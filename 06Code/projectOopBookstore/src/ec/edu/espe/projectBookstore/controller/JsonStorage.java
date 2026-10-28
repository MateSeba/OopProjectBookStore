package ec.edu.espe.projectBookstore.controller;

import ec.edu.espe.projectBookstore.model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public class JsonStorage {

    private final Path dir;

    public JsonStorage(String directory) {
        this.dir = Paths.get(directory);
    }

    public boolean hasData() {
        return Files.exists(dir.resolve("books.json"));
    }

    public void save(BookstoreData d) throws IOException {
        Files.createDirectories(dir);

        write("authors.json", list(d.authors, a -> map(
                "id", a.getId(), "name", a.getName(),
                "nationality", a.getNationality(), "biography", a.getBiography())));

        write("publishers.json", list(d.publishers, p -> map(
                "id", p.getId(), "name", p.getName(), "description", p.getDescription())));

        write("categories.json", list(d.categories, c -> map(
                "id", c.getId(), "name", c.getName(), "description", c.getDescription())));

        write("books.json", list(d.books, b -> map(
                "id", b.getId(), "title", b.getTitle(), "isbn", b.getIsbn(),
                "price", b.getPrice(), "stock", b.getStock(), "description", b.getDescription(),
                "authorId", b.getAuthor() == null ? null : b.getAuthor().getId(),
                "publisherId", b.getPublisher() == null ? null : b.getPublisher().getId(),
                "categoryId", b.getCategory() == null ? null : b.getCategory().getId())));

        write("customers.json", list(d.customers, c -> map(
                "id", c.getId(), "name", c.getName(), "email", c.getEmail(),
                "phone", c.getPhone(), "age", c.getAge(), "password", c.getPassword(),
                "address", addressToMap(c.getAddress()))));

        write("orders.json", list(d.orders, o -> map(
                "id", o.getId(), "date", time(o.getOrderDate()), "total", o.getTotal(),
                "status", o.getStatus(), "paymentMethod", o.getPaymentMethod(),
                "customerId", o.getCustomer() == null ? null : o.getCustomer().getId(),
                "shippingAddress", addressToMap(o.getShippingAddress()),
                "details", list(o.getDetails(), x -> map(
                        "id", x.getId(), "bookId", x.getBook() == null ? null : x.getBook().getId(),
                        "quantity", x.getQuantity(), "unitPrice", x.getUnitPrice())))));

        write("payments.json", list(d.payments, p -> map(
                "id", p.getId(), "amount", p.getAmount(), "date", time(p.getPaymentDate()),
                "paymentMethod", p.getPaymentMethod(), "status", p.getStatus(),
                "referenceNumber", p.getReferenceNumber())));

        write("drivers.json", list(d.drivers, x -> map(
                "id", x.getId(), "name", x.getName(), "vehiclePlate", x.getVehiclePlate(),
                "phone", x.getPhone(), "status", x.getStatus(),
                "currentLocation", addressToMap(x.getCurrentLocation()))));

        write("deliveries.json", list(d.deliveryAssignments, a -> map(
                "id", a.getId(),
                "orderId", a.getOrder() == null ? null : a.getOrder().getId(),
                "driverId", a.getDriver() == null ? null : a.getDriver().getId(),
                "dispatchTime", time(a.getDispatchTime()), "status", a.getStatus())));
    }

    public BookstoreData load() throws IOException {
        BookstoreData d = new BookstoreData();

        for (Map<String, Object> m : read("authors.json")) {
            d.authors.add(new Author(i(m, "id"), s(m, "name"), s(m, "nationality"), s(m, "biography")));
        }
        for (Map<String, Object> m : read("publishers.json")) {
            d.publishers.add(new Publisher(i(m, "id"), s(m, "name"), s(m, "description")));
        }
        for (Map<String, Object> m : read("categories.json")) {
            d.categories.add(new Category(i(m, "id"), s(m, "name"), s(m, "description")));
        }
        for (Map<String, Object> m : read("books.json")) {
            Author author = find(d.authors, i(m, "authorId"), Author::getId);
            Publisher publisher = find(d.publishers, i(m, "publisherId"), Publisher::getId);
            Category category = find(d.categories, i(m, "categoryId"), Category::getId);
            d.books.add(new Book(i(m, "id"), s(m, "title"), s(m, "isbn"), f(m, "price"),
                    i(m, "stock"), s(m, "description"), author, publisher, category));
        }
        for (Map<String, Object> m : read("customers.json")) {
            d.customers.add(new Customer(i(m, "id"), s(m, "name"), s(m, "email"), s(m, "phone"),
                    i(m, "age"), s(m, "password"), mapToAddress(m.get("address"))));
        }
        for (Map<String, Object> m : read("orders.json")) {
            Order order = new Order(i(m, "id"), date(m, "date"), f(m, "total"),
                    mapToAddress(m.get("shippingAddress")), s(m, "status"), s(m, "paymentMethod"));
            order.setCustomer(find(d.customers, i(m, "customerId"), Customer::getId));
            for (Map<String, Object> x : maps(m.get("details"))) {
                Book book = find(d.books, i(x, "bookId"), Book::getId);
                order.addDetail(new OrderDetail(i(x, "id"), i(x, "quantity"), book, f(x, "unitPrice")));
            }
            d.orders.add(order);
        }
        for (Map<String, Object> m : read("payments.json")) {
            d.payments.add(new Payment(i(m, "id"), f(m, "amount"), date(m, "date"),
                    s(m, "paymentMethod"), s(m, "status"), s(m, "referenceNumber")));
        }
        for (Map<String, Object> m : read("drivers.json")) {
            d.drivers.add(new DeliveryDriver(s(m, "id"), s(m, "name"), s(m, "vehiclePlate"),
                    s(m, "phone"), s(m, "status"), mapToAddress(m.get("currentLocation"))));
        }
        for (Map<String, Object> m : read("deliveries.json")) {
            Order order = find(d.orders, i(m, "orderId"), Order::getId);
            DeliveryDriver driver = null;
            for (DeliveryDriver candidate : d.drivers) {
                if (candidate.getId().equals(s(m, "driverId"))) {
                    driver = candidate;
                }
            }
            d.deliveryAssignments.add(new DeliveryAssignment(i(m, "id"), order, driver,
                    date(m, "dispatchTime"), s(m, "status")));
        }
        return d;
    }

    private void write(String file, Object content) throws IOException {
        Files.writeString(dir.resolve(file), JsonUtil.toJson(content), StandardCharsets.UTF_8);
    }

    @SuppressWarnings("unchecked")
    private List<Map<String, Object>> read(String file) throws IOException {
        Path path = dir.resolve(file);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }
        String text = Files.readString(path, StandardCharsets.UTF_8);
        if (text.isBlank()) {
            return new ArrayList<>();
        }
        return maps(JsonUtil.parse(text));
    }

    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> maps(Object value) {
        List<Map<String, Object>> result = new ArrayList<>();
        if (value instanceof List<?> items) {
            for (Object item : items) {
                if (item instanceof Map) {
                    result.add((Map<String, Object>) item);
                }
            }
        }
        return result;
    }

    private static <T> List<Map<String, Object>> list(List<T> items, Function<T, Map<String, Object>> converter) {
        List<Map<String, Object>> result = new ArrayList<>();
        for (T item : items) {
            result.add(converter.apply(item));
        }
        return result;
    }

    private static Map<String, Object> map(Object... keyValues) {
        Map<String, Object> m = new LinkedHashMap<>();
        for (int k = 0; k < keyValues.length; k += 2) {
            m.put((String) keyValues[k], keyValues[k + 1]);
        }
        return m;
    }

    private static Map<String, Object> addressToMap(Address a) {
        if (a == null) {
            return null;
        }
        return map("mainStreet", a.getMainStreet(), "secondaryStreet", a.getSecondaryStreet(),
                "sector", a.getSector(), "reference", a.getReference());
    }

    @SuppressWarnings("unchecked")
    private static Address mapToAddress(Object value) {
        if (!(value instanceof Map)) {
            return null;
        }
        Map<String, Object> m = (Map<String, Object>) value;
        return new Address(s(m, "mainStreet"), s(m, "secondaryStreet"), s(m, "sector"), s(m, "reference"));
    }

    private static <T> T find(List<T> items, int id, ToIntFunction<T> idOf) {
        for (T item : items) {
            if (idOf.applyAsInt(item) == id) {
                return item;
            }
        }
        return null;
    }

    private static Long time(Date date) {
        return date == null ? null : date.getTime();
    }

    private static Date date(Map<String, Object> m, String key) {
        Object v = m.get(key);
        return v instanceof Number n ? new Date(n.longValue()) : null;
    }

    private static int i(Map<String, Object> m, String key) {
        Object v = m.get(key);
        return v instanceof Number n ? n.intValue() : 0;
    }

    private static float f(Map<String, Object> m, String key) {
        Object v = m.get(key);
        return v instanceof Number n ? n.floatValue() : 0f;
    }

    private static String s(Map<String, Object> m, String key) {
        Object v = m.get(key);
        return v == null ? null : v.toString();
    }

    private static boolean b(Map<String, Object> m, String key) {
        return m.get(key) instanceof Boolean flag && flag;
    }
}
