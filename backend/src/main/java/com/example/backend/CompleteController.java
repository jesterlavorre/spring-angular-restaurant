package com.example.backend;

import java.security.cert.CertPathValidatorException.BasicReason;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.db.DB;
import com.example.backend.models.AdminData;
import com.example.backend.models.BasicResponse;
import com.example.backend.models.ChangePassword;
import com.example.backend.models.Comment;
import com.example.backend.models.ConfirmReservation;
import com.example.backend.models.DeliveryOrdersWaiter;
import com.example.backend.models.DeliveryView;
import com.example.backend.models.DiagramBars;
import com.example.backend.models.DiagramHistogram;
import com.example.backend.models.DiagramPie;
import com.example.backend.models.Diagrams;
import com.example.backend.models.Dishes;
import com.example.backend.models.Home;
import com.example.backend.models.Ingredient;
import com.example.backend.models.InsertNewRestaurant;
import com.example.backend.models.LinkWaiterRestaurant;
import com.example.backend.models.Login;
import com.example.backend.models.OperatingHours;
import com.example.backend.models.Order;
import com.example.backend.models.OrderDishWaiter;
import com.example.backend.models.Reservation;
import com.example.backend.models.ReservationsView;
import com.example.backend.models.ReservationsWaiter;
import com.example.backend.models.Restaurant;
import com.example.backend.models.RestaurantInfo;
import com.example.backend.models.RestaurantsForAdmin;
import com.example.backend.models.UpdateOrderStatus;
import com.example.backend.models.UpdateUserStatus;
import com.example.backend.models.User;
import com.example.backend.models.UsersForAdmin;
import com.example.backend.models.WorksAtRestaurant;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/controller")
@CrossOrigin(origins = "http://localhost:4200/")
public class CompleteController {

    @PostMapping("login")
    public User login(@RequestBody Login data) throws SQLException {

        User user = null;

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement("SELECT \r\n" + //
                        "    STATUS_ID,\r\n" + //
                        "    CREDIT_CARD_NO,\r\n" + //
                        "    PROFILE_PICTURE,\r\n" + //
                        "    EMAIL,\r\n" + //
                        "    CONTACT_PHONE,\r\n" + //
                        "    SEX,\r\n" + //
                        "    LAST_NAME,\r\n" + //
                        "    FIRST_NAME,\r\n" + //
                        "    SECURITY_A,\r\n" + //
                        "    SECURITY_Q,\r\n" + //
                        "    ACCOUNT_TYPE_ID,\r\n" + //
                        "    PASSWORD,\r\n" + //
                        "    PASSWORD_CLEAR,\r\n" + //
                        "    USERNAME, \r\n" + //
                        "    ADDRESS \r\n" + //
                        "FROM Users\r\n" + //
                        "WHERE USERNAME = ? and PASSWORD = ? and ACCOUNT_TYPE_ID in (1,2,3)")) {
            stm.setString(1, data.getU());
            stm.setString(2, data.getP());
            // stm.setString(3, data.getT());
            System.err.println(stm);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                user = new User(rs.getString("STATUS_ID"), rs.getString("CREDIT_CARD_NO"),
                        rs.getString("PROFILE_PICTURE"), rs.getString("EMAIL"), rs.getString("CONTACT_PHONE"),
                        rs.getString("SEX"), rs.getString("LAST_NAME"), rs.getString("FIRST_NAME"),
                        rs.getString("SECURITY_A"), rs.getString("SECURITY_Q"), rs.getString("ACCOUNT_TYPE_ID"),
                        rs.getString("PASSWORD"), rs.getString("PASSWORD_CLEAR"), rs.getString("USERNAME"),
                        rs.getString("ADDRESS"));
            }
        }
        return user;
    }

    @PostMapping("new-user")
    public BasicResponse prijaviSe(@RequestBody User newUser) throws SQLException {

        int id = 0;
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement(
                        "INSERT INTO users (STATUS_ID, CREDIT_CARD_NO, PROFILE_PICTURE, EMAIL, CONTACT_PHONE, SEX, LAST_NAME, FIRST_NAME, SECURITY_A, SECURITY_Q, ACCOUNT_TYPE_ID, PASSWORD, PASSWORD_CLEAR, USERNAME, INSERT_TIME,ADDRESS) "
                                +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(),?)",
                        Statement.RETURN_GENERATED_KEYS);

        ) {
            stm.setString(1, newUser.getStatusId());
            stm.setString(2, newUser.getCreditCardNo());
            stm.setString(3, newUser.getProfilePicture());
            stm.setString(4, newUser.getEmail());
            stm.setString(5, newUser.getContactPhone());
            stm.setString(6, newUser.getSex());
            stm.setString(7, newUser.getLastName());
            stm.setString(8, newUser.getFirstName());
            stm.setString(9, newUser.getSecurityA());
            stm.setString(10, newUser.getSecurityQ());
            stm.setString(11, newUser.getAccountTypeId());
            stm.setString(12, newUser.getPassword());
            stm.setString(13, newUser.getPasswordClear());
            stm.setString(14, newUser.getUsername());
            stm.setString(15, newUser.getAddress());
            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1); // Assuming the ID is a long type

            }
        }

        return new BasicResponse(id + "", id + "");

    }

    @PutMapping("update-user")
    public User updateUserByUsername(@RequestBody User updatedUser) throws SQLException {

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement(
                        "UPDATE users SET " +
                                "STATUS_ID = ?, " +
                                "CREDIT_CARD_NO = ?, " +
                                "PROFILE_PICTURE = ?, " +
                                "EMAIL = ?, " +
                                "CONTACT_PHONE = ?, " +
                                "SEX = ?, " +
                                "LAST_NAME = ?, " +
                                "FIRST_NAME = ?, " +
                                "SECURITY_A = ?, " +
                                "SECURITY_Q = ?, " +
                                "ACCOUNT_TYPE_ID = ?, " +
                                "PASSWORD = ?, " +
                                "PASSWORD_CLEAR = ?, " +
                                "ADDRESS = ? " +
                                "WHERE USERNAME = ?");

        ) {
            stm.setString(1, updatedUser.getStatusId());
            stm.setString(2, updatedUser.getCreditCardNo());
            stm.setString(3, updatedUser.getProfilePicture());
            stm.setString(4, updatedUser.getEmail());
            stm.setString(5, updatedUser.getContactPhone());
            stm.setString(6, updatedUser.getSex());
            stm.setString(7, updatedUser.getLastName());
            stm.setString(8, updatedUser.getFirstName());
            stm.setString(9, updatedUser.getSecurityA());
            stm.setString(10, updatedUser.getSecurityQ());
            stm.setString(11, updatedUser.getAccountTypeId());
            stm.setString(12, updatedUser.getPassword());
            stm.setString(13, updatedUser.getPasswordClear());
            stm.setString(14, updatedUser.getAddress());
            stm.setString(15, updatedUser.getUsername());

            int rowsUpdated = stm.executeUpdate();

            if (rowsUpdated > 0) {
                // Update was successful, return the updated user object
                return updatedUser;
            }
        }

        // Return null or handle appropriately if no update occurred
        return null;
    }

    @GetMapping("user-exists")
    public User getUserByEmailOrUsername(@RequestParam String param1,
            @RequestParam String param2) throws SQLException {

        System.err.println(param1 + param2);
        User user = null;

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement("SELECT \r\n" + //
                        "    STATUS_ID,\r\n" + //
                        "    CREDIT_CARD_NO,\r\n" + //
                        "    PROFILE_PICTURE,\r\n" + //
                        "    EMAIL,\r\n" + //
                        "    CONTACT_PHONE,\r\n" + //
                        "    SEX,\r\n" + //
                        "    LAST_NAME,\r\n" + //
                        "    FIRST_NAME,\r\n" + //
                        "    SECURITY_A,\r\n" + //
                        "    SECURITY_Q,\r\n" + //
                        "    ACCOUNT_TYPE_ID,\r\n" + //
                        "    PASSWORD,\r\n" + //
                        "    PASSWORD_CLEAR,\r\n" + //
                        "    USERNAME, \r\n" + //
                        "    ADDRESS \r\n" + //
                        "FROM Users\r\n" + //
                        "WHERE (USERNAME = ? OR EMAIL = ?) and status_id in (1,4)")) {
            stm.setString(1, param2);
            stm.setString(2, param1);
            ResultSet rs = stm.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = rsmd.getColumnName(i);
                System.out.println("Column " + i + ": " + columnName);
            }
            if (rs.next()) {

                user = new User(rs.getString("STATUS_ID"), rs.getString("CREDIT_CARD_NO"),
                        rs.getString("PROFILE_PICTURE"), rs.getString("EMAIL"), rs.getString("CONTACT_PHONE"),
                        rs.getString("SEX"), rs.getString("LAST_NAME"), rs.getString("FIRST_NAME"),
                        rs.getString("SECURITY_A"), rs.getString("SECURITY_Q"), rs.getString("ACCOUNT_TYPE_ID"),
                        rs.getString("PASSWORD"), rs.getString("PASSWORD_CLEAR"), rs.getString("USERNAME"),
                        rs.getString("ADDRESS"));
            }
        }
        return user;

    }

    @PostMapping("change-password")
    public User changePassword(@RequestBody ChangePassword changePassword) throws SQLException {

        System.err.println(changePassword.getUsername());
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement(
                        "UPDATE users SET PASSWORD = ?, PASSWORD_CLEAR = ? WHERE username = ?")) {

            stm.setString(1, changePassword.getNewPassword());
            stm.setString(2, changePassword.getNewPassword());
            stm.setString(3, changePassword.getUsername());
            stm.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            e.printStackTrace();
        }

        return null;

    }

    @GetMapping("home-page")
    public Home getHomePage() throws SQLException {
        String numberOfRestaurants = "";
        String numberOfRegisteredUsers = "";
        String numberOfWaitingUsers = "";
        String numberOfReservations24h = "";
        String numberOfReservations7d = "";
        String numberOfReservations30d = "";

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement("SELECT " +
                        "(SELECT COUNT(*) FROM users WHERE status_id = '1') AS registered_users, " +
                        "(SELECT COUNT(*) FROM restaurant) AS restaurants, " +
                        "(SELECT COUNT(*) FROM users WHERE status_id = '0') AS waiting_users ")) {

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                numberOfRegisteredUsers = rs.getString("registered_users");
                numberOfRestaurants = rs.getString("restaurants");
                numberOfWaitingUsers = rs.getString("waiting_users");
                // Note: If needed, handle the 'some_other_count' similarly
            }
        }

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement("SELECT " +
                        "(SELECT COUNT(*) FROM reservations WHERE insert_time >= NOW() - INTERVAL 1 DAY) AS count_last_24h, "
                        +
                        "(SELECT COUNT(*) FROM reservations WHERE insert_time >= NOW() - INTERVAL 7 DAY) AS count_last_7d, "
                        +
                        "(SELECT COUNT(*) FROM reservations WHERE insert_time >= NOW() - INTERVAL 1 MONTH) AS count_last_30d")) {

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                numberOfReservations24h = rs.getString("count_last_24h");
                numberOfReservations7d = rs.getString("count_last_7d");
                numberOfReservations30d = rs.getString("count_last_30d");
            }
        }

        List<Restaurant> restaurantList = new ArrayList<>();

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement("SELECT id, name, address, type FROM restaurant")) {

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getString("id"));
                restaurant.setName(rs.getString("name"));
                restaurant.setAddress(rs.getString("address"));
                restaurant.setType(rs.getString("type"));

                try (
                        PreparedStatement stmRating = conn.prepareStatement(
                                "SELECT avg(c.rated) from restaurant r, comments c where r.id=c.restaurant_id and r.id = ?")) {
                    stmRating.setString(1, restaurant.getId());
                    ResultSet rsRating = stmRating.executeQuery();

                    if (rsRating.next()) {
                        restaurant.setRated(rsRating.getString(1));
                    }
                }

                // Initialize waitersList for each restaurant
                List<User> waitersList = new ArrayList<>();

                try (PreparedStatement stmWaiters = conn.prepareStatement(
                        "SELECT u.FIRST_NAME, u.LAST_NAME FROM users u, waiter_restaurant wr " +
                                "WHERE u.id = wr.waiter_id AND wr.restaurant_id = ?")) {

                    stmWaiters.setString(1, restaurant.getId());
                    ResultSet rsWaiters = stmWaiters.executeQuery();

                    while (rsWaiters.next()) {
                        User waiter = new User();
                        waiter.setFirstName(rsWaiters.getString("FIRST_NAME"));
                        waiter.setLastName(rsWaiters.getString("LAST_NAME"));
                        waitersList.add(waiter);
                    }
                }

                // Set waitersList for the current restaurant
                restaurant.setWaiters(waitersList);

                // Add restaurant to the list
                restaurantList.add(restaurant);
            }
        }

        Home home = new Home();
        home.setNumberOfRegisteredUsers(numberOfRegisteredUsers);
        home.setNumberOfRestaurants(numberOfRestaurants);
        home.setNumberOfWaitingUsers(numberOfWaitingUsers);
        home.setNumberOfReservations24h(numberOfReservations24h);
        home.setNumberOfReservations30d(numberOfReservations30d);
        home.setNumberOfReservations7d(numberOfReservations7d);
        home.setRestaurants(restaurantList);
        return home;

    }

    @GetMapping("restaurant-info-comments")
    public RestaurantInfo getMethodName(@RequestParam String param) throws SQLException {
        RestaurantInfo restInfo = new RestaurantInfo();
        Restaurant rest = new Restaurant();
        restInfo.setRestaurant(rest);
        List<Comment> comments = new ArrayList<>();
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "select name,address,type,phone_number,embedded_map from restaurant where id=?")) {
            stm.setString(1, param);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                restInfo.getRestaurant().setName(rs.getString("name"));
                restInfo.getRestaurant().setAddress(rs.getString("address"));
                restInfo.getRestaurant().setType(rs.getString("type"));
                restInfo.getRestaurant().setPhoneNumber(rs.getString("phone_number"));
                restInfo.getRestaurant().setEmbeddedMap(rs.getString("embedded_map"));
            }
        }

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement(
                        "select CONCAT(u.FIRST_NAME, ' ', u.LAST_NAME) as fullname, c.TEXT as text, c.RATED as rated,c.INSERT_TIME as insertTime\n"
                                + //
                                "from users u, restaurant r, comments c\n" + //
                                "where c.RESTAURANT_ID = r.id and c.USER_ID=u.id and r.id = ?")) {
            stm.setString(1, param);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentBy(rs.getString("fullname"));
                comment.setText(rs.getString("text"));
                comment.setRating(rs.getString("rated"));
                comment.setInsertTime(rs.getString("insertTime"));
                comments.add(comment);
            }
        }
        restInfo.setComments(comments);

        return restInfo;
    }

    @PostMapping("make-reservation")
    public BasicResponse makeReservation(@RequestBody Reservation entity) throws SQLException {

        BasicResponse basicRes = new BasicResponse("", "");
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "SELECT \n" + //
                                        "    CASE\n" + //
                                        "WHEN ? < CURDATE() THEN 'Error: Date in the past'\n" +
                                        "        WHEN (SELECT 1\n" + //
                                        "              FROM operating_hours oh\n" + //
                                        "              WHERE oh.RESTAURANT_ID = r.ID\n" + //
                                        "                AND oh.DAY_OF_WEEK = DAYNAME(?)\n" + //
                                        "                AND ? BETWEEN oh.OPENS_AT AND oh.CLOSES_AT) IS NULL THEN 'Closed'\n"
                                        + //
                                        "        ELSE 'Open'\n" + //
                                        "    END AS status\n" + //
                                        "FROM \n" + //
                                        "    restaurant r\n" + //
                                        "WHERE \n" + //
                                        "    r.ID = ?\n" + //
                                        "")) {
            stm.setString(1, entity.getWhen());
            stm.setString(2, entity.getWhen());// DAYNAME('2024-06-22 19:00:00')
            stm.setString(3, entity.getWhen().split("\\s", 2)[1]);// '19:00:00'
            stm.setString(4, entity.getForRestaurant());
            System.err.println(stm);
            ResultSet rs = stm.executeQuery();

            String userId = "";
            if (rs.next()) {
                basicRes.setStatusMessage(rs.getString("status"));
                if (basicRes.getStatusMessage().equals("Error: Date in the past")) {
                    basicRes.setStatus("ERROR");
                    return basicRes;
                } else if (basicRes.getStatusMessage().equals("Closed")) {
                    basicRes.setStatus("ERROR");
                    return basicRes;
                }
            }
            String maxNumberOfPeople = "";
            try (Connection conn6 = DB.source().getConnection();
                    PreparedStatement stm6 = conn
                            .prepareStatement(
                                    "SELECT max_number_of_people from restaurant where id=?")) {
                stm6.setString(1, entity.getForRestaurant());
                ResultSet rs6 = stm6.executeQuery();

                if (rs6.next()) {
                    maxNumberOfPeople = rs6.getString(1);
                }
            }

            try (Connection conn5 = DB.source().getConnection();
                    PreparedStatement stm5 = conn5.prepareStatement(
                            "SELECT SUM(res.number_of_guests) AS total_guests " +
                                    "FROM reservations res " +
                                    "WHERE res.arrive_at BETWEEN DATE_SUB(?, INTERVAL 3 HOUR) AND ? " +
                                    "AND res.status IN (10, 50)")) {

                stm5.setString(1, entity.getWhen());
                stm5.setString(2, entity.getWhen());

                try (ResultSet rs5 = stm5.executeQuery()) {
                    if (rs5.next()) {
                        int totalGuests = rs5.getInt(1);
                        int newGuests = Integer.parseInt(entity.getNumberOfGuests());
                        int maxCapacity = Integer.parseInt(maxNumberOfPeople);

                        if ((totalGuests + newGuests) > maxCapacity) {
                            basicRes.setStatusMessage("Not enought capacity");
                            return basicRes;
                        }
                    }
                }
            }

            try (Connection conn7 = DB.source().getConnection();
                    PreparedStatement stm7 = conn
                            .prepareStatement(
                                    "SELECT SUM(res.number_of_guests) AS total_guests\r\n" + //
                                            "FROM reservations res\r\n" + //
                                            "WHERE res.arrive_at BETWEEN ? AND DATE_ADD(?, INTERVAL 3 HOUR)\r\n" + //
                                            "AND res.status IN (10, 50);\r\n" + //
                                            "")) {
                stm7.setString(1, entity.getWhen());
                stm7.setString(2, entity.getWhen());
                ResultSet rs7 = stm7.executeQuery();
                if (rs7.next()) {

                    if ((rs7.getInt(1) + Integer
                            .parseInt(entity.getNumberOfGuests()) > Integer.parseInt(maxNumberOfPeople))) {
                        basicRes.setStatusMessage("Not enought capacity");
                        return basicRes;
                    }

                }
            }
            basicRes.setStatusMessage("Open");
            if (basicRes.getStatusMessage().equals("Open")) {
                basicRes.setStatus("OK");
                try (Connection conn2 = DB.source().getConnection();
                        PreparedStatement stm2 = conn2
                                .prepareStatement(
                                        "select id from users where username = ?")) {
                    stm2.setString(1, entity.getMadeBy());
                    ResultSet rs2 = stm2.executeQuery();
                    if (rs2.next()) {
                        userId = rs2.getString("id");
                    }
                }
                try (Connection conn1 = DB.source().getConnection();
                        PreparedStatement stm1 = conn1.prepareStatement(
                                "INSERT INTO reservations (RESTAURANT_ID, ARRIVE_AT, EXTRA_COMMENT, NUMBER_OF_GUESTS, MADE_BY_USER_ID, INSERT_TIME,STATUS) values(?,?,?,?,?,NOW(),'20')")) {

                    stm1.setString(1, entity.getForRestaurant());
                    stm1.setString(2, entity.getWhen());
                    stm1.setString(3, entity.getExtraComment());
                    stm1.setString(4, entity.getNumberOfGuests());
                    stm1.setString(5, userId);
                    System.err.println(stm1);
                    int rowsAffected = stm1.executeUpdate();
                    System.out.println("Rows affected: " + rowsAffected);

                } catch (SQLException e) {
                    // Handle SQL exceptions
                    e.printStackTrace();
                }
                return basicRes;
            }

        }
        return null;
    }

    @GetMapping("all-dishes")
    public List<Dishes> getAllDishes(@RequestParam String restaurantId) throws SQLException {
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement(
                        "select d.id,d.name,d.price,d.picture from dish d, restaurant r where d.restaurant_id=r.id and r.id=?")) {

            stm.setString(1, restaurantId);

            ResultSet rs = stm.executeQuery();

            List<Dishes> dishList = new ArrayList<>();

            while (rs.next()) {
                Dishes dish = new Dishes();
                dish.setId(rs.getString("id"));
                dish.setName(rs.getString("name"));
                dish.setPrice(rs.getString("price"));
                dish.setPicture(rs.getString("picture"));
                dish.setIngredients(new ArrayList<>());

                try (Connection conn1 = DB.source().getConnection();
                        PreparedStatement stm1 = conn1.prepareStatement(
                                "select i.name,i.amount from dish d, ingredients i where i.dish_id=d.id and d.id=?")) {

                    stm1.setString(1, dish.getId());

                    ResultSet rs1 = stm1.executeQuery();
                    while (rs1.next()) {
                        Ingredient ing = new Ingredient(rs1.getString("name"), rs1.getString("amount"));
                        dish.getIngredients().add(ing);
                    }
                }

                dishList.add(dish);
            }

            return dishList;

        }
    }

    @PostMapping("make-order")
    public BasicResponse postMethodName(@RequestBody List<Order> entity) throws SQLException {

        String userId = "";
        String restaurantId = "";
        for (Order order : entity) {

            try (Connection conn2 = DB.source().getConnection();
                    PreparedStatement stm2 = conn2
                            .prepareStatement(
                                    "select id from users where username = ?")) {
                stm2.setString(1, order.getMadeByUsername());
                ResultSet rs2 = stm2.executeQuery();
                if (rs2.next()) {
                    userId = rs2.getString("id");
                }
            }
            restaurantId = order.getRestaurantId();

        }
        int id = 0;
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "insert into delivery_orders(USER_ID,RESTAURANT_ID,INSERT_TIME,STATUS) values (?,?,NOW(),'100')",
                                Statement.RETURN_GENERATED_KEYS)) {
            stm.setString(1, userId);
            stm.setString(2, restaurantId);
            stm.executeUpdate();
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1); // Assuming the ID is a long type
                System.out.println("Generated ID: " + id);
                // Use the generated ID as needed
            }
        }
        for (Order order : entity) {
            try (Connection conn = DB.source().getConnection();
                    PreparedStatement stm = conn
                            .prepareStatement(
                                    "insert into delivery_orders_items(DISH_ID,AMOUNT,DELIVERY_ORDER_ID) values (?,?,?)")) {
                stm.setString(1, order.getDishId());
                stm.setString(2, order.getAmount());
                stm.setString(3, id + "");
                stm.executeUpdate();

            }
        }
        return new BasicResponse("OK", "OK");
    }

    @GetMapping("user-reservations")
    public ReservationsView getAllUserReservations(@RequestParam String username) throws SQLException {
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn.prepareStatement(
                        "select res.name,rez.extra_comment,rez.number_of_guests,rez.ARRIVE_AT,rez.INSERT_TIME,res.address\n"
                                + //
                                "from reservations rez,restaurant res, users u\n" + //
                                "where rez.RESTAURANT_ID = res.id and rez.MADE_BY_USER_ID = u.id and u.username = ? and rez.status in ('10')")) {

            stm.setString(1, username);

            ResultSet rs = stm.executeQuery();

            ReservationsView resView = new ReservationsView();
            List<Reservation> activReservations = new ArrayList<>();
            while (rs.next()) {
                // String madeBy;
                // String forRestaurant;
                // String extraComment;
                // String numberOfGuests;
                // String when;
                // String comment;
                // String insertTime;
                Reservation res = new Reservation();
                res.setMadeBy(username);
                res.setForRestaurant(rs.getString("name"));
                res.setExtraComment(rs.getString("extra_comment"));
                res.setNumberOfGuests(rs.getString("number_of_guests"));
                res.setWhen(rs.getString("arrive_at"));
                res.setComment("");
                res.setInsertTime(rs.getString("insert_time"));
                res.setAddress(rs.getString("address"));
                activReservations.add(res);
            }
            resView.setActiveReservations(activReservations);

            try (Connection conn1 = DB.source().getConnection();
                    PreparedStatement stm1 = conn1.prepareStatement(
                            "select res.name,rez.extra_comment,rez.number_of_guests,rez.ARRIVE_AT,rez.INSERT_TIME,res.address\n"
                                    + //
                                    "from reservations rez,restaurant res, users u\n" + //
                                    "where rez.RESTAURANT_ID = res.id and rez.MADE_BY_USER_ID = u.id and u.username = ? and rez.status = '60'")) {

                stm1.setString(1, username);

                ResultSet rs1 = stm1.executeQuery();

                List<Reservation> finishedReservations = new ArrayList<>();
                while (rs1.next()) {
                    // String madeBy;
                    // String forRestaurant;
                    // String extraComment;
                    // String numberOfGuests;
                    // String when;
                    // String comment;
                    // String insertTime;
                    Reservation res = new Reservation();
                    res.setMadeBy(username);
                    res.setForRestaurant(rs1.getString("name"));
                    res.setExtraComment(rs1.getString("extra_comment"));
                    res.setNumberOfGuests(rs1.getString("number_of_guests"));
                    res.setWhen(rs1.getString("arrive_at"));
                    res.setComment("");
                    res.setInsertTime(rs1.getString("insert_time"));
                    res.setAddress(rs1.getString("address"));
                    finishedReservations.add(res);
                }
                resView.setFinishedReservations(finishedReservations);

                return resView;

            }
        }
    }

    @GetMapping("delivery-view")
    public List<DeliveryView> getAllDeliveriesByUsername(@RequestParam String username) throws SQLException {
        List<DeliveryView> deliveryView = new ArrayList<>();
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "select r.name,do.insert_time,s.status,do.ESTIMATED_DELIVERY,do.ACTUAL_DELIVERY\n" + //
                                        "from users u , delivery_orders do, restaurant r,status s\n" + //
                                        "where do.user_id = u.id and do.RESTAURANT_ID = r.id and u.USERNAME = ? and do.status = s.id and s.id in (110,120)")) {
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                deliveryView
                        .add(new DeliveryView(rs.getString("name"), rs.getString("insert_time"), rs.getString("status"),
                                rs.getString("estimated_delivery"), rs.getString("actual_delivery")));
            }
        }

        return deliveryView;
    }

    @GetMapping("waiter-reservations")
    public List<ReservationsWaiter> getWaiterReservations(@RequestParam String username) throws SQLException {
        List<ReservationsWaiter> waiterReservations = new ArrayList<>();

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "select r.id,r.NUMBER_OF_GUESTS,r.INSERT_TIME,r.EXTRA_COMMENT,r.ARRIVE_AT, s.status\n" + //
                                        "from users u, waiter_restaurant wr,reservations r,restaurant res, status s\n" + //
                                        "where u.id=wr.waiter_id and wr.restaurant_id = res.id and res.id = r.restaurant_id and u.username = ? and r.status = s.id and r.status not in ('30','60','70', '40')\n"
                                        + //
                                        "order by r.ARRIVE_AT desc")) {
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                waiterReservations
                        .add(new ReservationsWaiter(rs.getString("id"), rs.getString("number_of_guests"),
                                rs.getString("insert_time"), rs.getString("extra_comment"), rs.getString("arrive_at"),
                                rs.getString("status")));
            }
        }

        return waiterReservations;
    }

    @PostMapping("waiter-confirm-reservation")
    public BasicResponse confirmReservation(@RequestBody ConfirmReservation confRes)
            throws SQLException {
        String userId = "";
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "select id from users where username = ?")) {
            stm2.setString(1, confRes.getUsername());
            ResultSet rs2 = stm2.executeQuery();
            if (rs2.next()) {
                userId = rs2.getString("id");
            }
        }
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "update reservations set status = ?, confirmed_by = ?, deny_comment = ? where id = ?")) {
            stm.setString(1, confRes.getStatus());
            stm.setString(2, userId);
            stm.setString(3, confRes.getDenyReason());
            stm.setString(4, confRes.getReservatioId());
            stm.executeUpdate();

        }
        return new BasicResponse("OK", "OK");
    }

    @GetMapping("works-at-restaurant")
    public WorksAtRestaurant getWorksAtRestaurant(@RequestParam String username) throws SQLException {

        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "select r.id,r.LAYOUT,r.TABLES_TAKEN\n" + //
                                        "from users u, restaurant r, waiter_restaurant wr\n" + //
                                        "where u.id = wr.WAITER_ID and wr.RESTAURANT_ID = r.id and u.USERNAME = ?")) {
            stm2.setString(1, username);
            System.err.println(stm2);
            ResultSet rs2 = stm2.executeQuery();
            if (rs2.next()) {
                return new WorksAtRestaurant(rs2.getString("id"), rs2.getString("LAYOUT"),
                        rs2.getString("TABLES_TAKEN"));
            }

        }
        return null;
    }

    @PostMapping("update-layout")
    public BasicResponse updateLayout(@RequestBody WorksAtRestaurant worksAt) throws SQLException {
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "update restaurant set layout = ?, TABLES_TAKEN = ? where id = ?")) {
            stm2.setString(3, worksAt.getRestaurantId());
            stm2.setString(2, worksAt.getTablesSelected());
            stm2.setString(1, worksAt.getLayout());
            System.err.println(stm2);
            stm2.executeUpdate();

        }
        return new BasicResponse();
    }

    @GetMapping("waiter-delivery-orders")
    public List<DeliveryOrdersWaiter> getDeliveriOrdersWaiter(@RequestParam String restaurantId) throws SQLException {
        List<DeliveryOrdersWaiter> deliveryWaiter = new ArrayList<>();

        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "select do.id,do.INSERT_TIME,u.FIRST_NAME,u.LAST_NAME,u.CONTACT_PHONE\n" + //
                                        "from restaurant r, users u, delivery_orders do\n" + //
                                        "where r.id = do.RESTAURANT_ID and do.USER_ID = u.id and r.id = ? and do.status = '100'")) {
            stm2.setString(1, restaurantId);
            ResultSet rs2 = stm2.executeQuery();
            while (rs2.next()) {
                DeliveryOrdersWaiter delWai = new DeliveryOrdersWaiter();
                delWai.setOrderId(rs2.getString("id"));
                delWai.setOrderedWhen(rs2.getString("insert_time"));
                delWai.setOrderedBy(rs2.getString("first_name") + " " + rs2.getString("last_name"));
                delWai.setContactPhone(rs2.getString("contact_phone"));
                List<OrderDishWaiter> dishWai = new ArrayList<>();
                try (Connection conn = DB.source().getConnection();
                        PreparedStatement stm = conn
                                .prepareStatement(
                                        "select doi.AMOUNT,d.NAME\n" + //
                                                "from delivery_orders do, delivery_orders_items doi,dish d\n" + //
                                                "where do.id= doi.DELIVERY_ORDER_ID and doi.DISH_ID = d.id and do.id = ? ")) {
                    stm.setString(1, rs2.getString("id"));
                    System.err.println(stm);
                    ResultSet rs = stm.executeQuery();
                    while (rs.next()) {
                        dishWai.add(new OrderDishWaiter(rs.getString("amount"), rs.getString("name")));
                    }
                    delWai.setOrderDishList(dishWai);
                }
                deliveryWaiter.add(delWai);
            }
        }
        return deliveryWaiter;
    }

    @PostMapping("update-order-waiter")
    public BasicResponse postMethodName(@RequestBody UpdateOrderStatus entity) throws SQLException {

        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "update delivery_orders set status = ?, estimated_delivery = ? where id = ?")) {

            stm2.setString(1, entity.getStatus());
            stm2.setString(2, entity.getEstimatedDelivery());
            stm2.setString(3, entity.getOrderId());
            System.err.println(stm2);
            stm2.executeUpdate();

        }
        return new BasicResponse();
    }

    @GetMapping("diagrams")
    public Diagrams getMethodName(@RequestParam String username, @RequestParam String restaurantId)
            throws SQLException {
        Diagrams mainResponse = new Diagrams(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        List<DiagramBars> diagramBarsList = new ArrayList<>();

        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "SELECT \n" + //
                                        "    DAYNAME(r.arrive_at) AS day_name, \n" + //
                                        "    COUNT(r.arrive_at) AS number_of_reservations, \n" + //
                                        "    AVG(r.NUMBER_OF_GUESTS) AS total_guests\n" + //
                                        "FROM \n" + //
                                        "    reservations r, \n" + //
                                        "    users u \n" + //
                                        "WHERE \n" + //
                                        "    r.confirmed_by = u.id \n" + //
                                        "    AND u.USERNAME = ?\n" + //
                                        "GROUP BY \n" + //
                                        "    day_name\n" + //
                                        "ORDER BY \n" + //
                                        "    FIELD(day_name, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday');\n"
                                        + //
                                        "")) {

            stm2.setString(1, username);
            ResultSet rs2 = stm2.executeQuery();
            while (rs2.next()) {
                DiagramBars newDiagrambar = new DiagramBars(rs2.getString("day_name"),
                        rs2.getString("number_of_reservations"), rs2.getString("total_guests"));
                diagramBarsList.add(newDiagrambar);
            }

        }

        mainResponse.setDiagramBars(diagramBarsList);

        List<DiagramPie> diagramPieList = new ArrayList<>();

        try (Connection conn3 = DB.source().getConnection();
                PreparedStatement stm3 = conn3
                        .prepareStatement(
                                "SELECT SUM(r.NUMBER_OF_GUESTS) AS total_guests, CONCAT(u.FIRST_NAME, ' ', u.LAST_NAME) AS full_name, u.id FROM reservations r JOIN restaurant res ON r.RESTAURANT_ID = res.ID JOIN users u ON r.CONFIRMED_BY = u.id JOIN waiter_restaurant wr ON wr.RESTAURANT_ID = res.id AND wr.WAITER_ID = u.id WHERE res.id = ? GROUP BY u.USERNAME")) {

            stm3.setString(1, restaurantId);
            ResultSet rs3 = stm3.executeQuery();
            while (rs3.next()) {
                DiagramPie newDiagrampie = new DiagramPie(rs3.getString("full_name"), rs3.getString("total_guests"),
                        rs3.getString("id"));
                diagramPieList.add(newDiagrampie);
            }

        }

        mainResponse.setDiagramPie(diagramPieList);

        List<DiagramHistogram> diagramHistogramList = new ArrayList<>();

        try (Connection conn4 = DB.source().getConnection();
                PreparedStatement stm4 = conn4
                        .prepareStatement(
                                "WITH months AS\n" + //
                                        "  (SELECT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL (a.a + (10 * b.a) + (100 * c.a)) MONTH), '%Y-%m') AS month_of_year\n"
                                        + //
                                        "   FROM\n" + //
                                        "     (SELECT 0 AS a\n" + //
                                        "      UNION ALL SELECT 1\n" + //
                                        "      UNION ALL SELECT 2\n" + //
                                        "      UNION ALL SELECT 3\n" + //
                                        "      UNION ALL SELECT 4\n" + //
                                        "      UNION ALL SELECT 5\n" + //
                                        "      UNION ALL SELECT 6\n" + //
                                        "      UNION ALL SELECT 7\n" + //
                                        "      UNION ALL SELECT 8\n" + //
                                        "      UNION ALL SELECT 9) a\n" + //
                                        "   CROSS JOIN\n" + //
                                        "     (SELECT 0 AS a\n" + //
                                        "      UNION ALL SELECT 1\n" + //
                                        "      UNION ALL SELECT 2\n" + //
                                        "      UNION ALL SELECT 3\n" + //
                                        "      UNION ALL SELECT 4\n" + //
                                        "      UNION ALL SELECT 5\n" + //
                                        "      UNION ALL SELECT 6\n" + //
                                        "      UNION ALL SELECT 7\n" + //
                                        "      UNION ALL SELECT 8\n" + //
                                        "      UNION ALL SELECT 9) b\n" + //
                                        "   CROSS JOIN\n" + //
                                        "     (SELECT 0 AS a\n" + //
                                        "      UNION ALL SELECT 1\n" + //
                                        "      UNION ALL SELECT 2\n" + //
                                        "      UNION ALL SELECT 3\n" + //
                                        "      UNION ALL SELECT 4\n" + //
                                        "      UNION ALL SELECT 5\n" + //
                                        "      UNION ALL SELECT 6\n" + //
                                        "      UNION ALL SELECT 7\n" + //
                                        "      UNION ALL SELECT 8\n" + //
                                        "      UNION ALL SELECT 9) c\n" + //
                                        "   WHERE DATE_SUB(NOW(), INTERVAL (a.a + (10 * b.a) + (100 * c.a)) MONTH) >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 24 MONTH), '%Y-%m-01')\n"
                                        + //
                                        "   ORDER BY month_of_year\n" + //
                                        "   LIMIT 25)\n" + //
                                        "SELECT m.month_of_year,\n" + //
                                        "       COALESCE(AVG(counts.num_reservations), 0) AS average_reservations_per_month\n"
                                        + //
                                        "FROM months m\n" + //
                                        "LEFT JOIN\n" + //
                                        "  (SELECT DATE_FORMAT(arrive_at, '%Y-%m') AS month_of_year,\n" + //
                                        "          COUNT(*) AS num_reservations\n" + //
                                        "   FROM reservations\n" + //
                                        "   WHERE arrive_at >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 24 MONTH), '%Y-%m-01')\n"
                                        + //
                                        "     AND restaurant_id = ?\n" + //
                                        "   GROUP BY DATE_FORMAT(arrive_at, '%Y-%m')) AS counts ON m.month_of_year = counts.month_of_year\n"
                                        + //
                                        "GROUP BY m.month_of_year\n" + //
                                        "ORDER BY m.month_of_year;\n" + //
                                        "")) {

            stm4.setString(1, restaurantId);
            ResultSet rs4 = stm4.executeQuery();
            while (rs4.next()) {
                DiagramHistogram diaramHist = new DiagramHistogram(rs4.getString("month_of_year"),
                        rs4.getString("average_reservations_per_month"));
                diagramHistogramList.add(diaramHist);
            }

        }

        mainResponse.setDiagramHistogram(diagramHistogramList);
        return mainResponse;
    }

    @GetMapping("admin-data")
    public AdminData getAdminStuff()
            throws SQLException {
        AdminData response = new AdminData(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "SELECT \n" + //
                                        "    ID,\n" + //
                                        "    USERNAME,\n" + //
                                        "    PASSWORD_CLEAR,\n" + //
                                        "    PASSWORD,\n" + //
                                        "    ACCOUNT_TYPE_ID,\n" + //
                                        "    SECURITY_Q,\n" + //
                                        "    SECURITY_A,\n" + //
                                        "    FIRST_NAME,\n" + //
                                        "    LAST_NAME,\n" + //
                                        "    SEX,\n" + //
                                        "    CONTACT_PHONE,\n" + //
                                        "    EMAIL,\n" + //
                                        "    PROFILE_PICTURE,\n" + //
                                        "    CREDIT_CARD_NO,\n" + //
                                        "    STATUS_ID,\n" + //
                                        "    INSERT_TIME,\n" + //
                                        "    ADDRESS\n" + //
                                        "FROM users\n" + //
                                        "where STATUS_ID = '0' and ACCOUNT_TYPE_ID = '1'\n" + //
                                        "")) {

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                response.getWaitingForApproval().add(new UsersForAdmin(rs.getString("ID"), rs.getString("USERNAME"),
                        rs.getString("PASSWORD_CLEAR"), rs.getString("PASSWORD"), rs.getString("ACCOUNT_TYPE_ID"),
                        rs.getString("SECURITY_Q"), rs.getString("SECURITY_A"), rs.getString("FIRST_NAME"),
                        rs.getString("FIRST_NAME"), rs.getString("SEX"), rs.getString("CONTACT_PHONE"),
                        rs.getString("EMAIL"), rs.getString("PROFILE_PICTURE"), rs.getString("CREDIT_CARD_NO"),
                        rs.getString("STATUS_ID"), rs.getString("INSERT_TIME"), rs.getString("ADDRESS")));
            }

        }

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "SELECT \n" + //
                                        "    ID,\n" + //
                                        "    USERNAME,\n" + //
                                        "    PASSWORD_CLEAR,\n" + //
                                        "    PASSWORD,\n" + //
                                        "    ACCOUNT_TYPE_ID,\n" + //
                                        "    SECURITY_Q,\n" + //
                                        "    SECURITY_A,\n" + //
                                        "    FIRST_NAME,\n" + //
                                        "    LAST_NAME,\n" + //
                                        "    SEX,\n" + //
                                        "    CONTACT_PHONE,\n" + //
                                        "    EMAIL,\n" + //
                                        "    PROFILE_PICTURE,\n" + //
                                        "    CREDIT_CARD_NO,\n" + //
                                        "    STATUS_ID,\n" + //
                                        "    INSERT_TIME,\n" + //
                                        "    ADDRESS\n" + //
                                        "FROM users\n" + //
                                        "where STATUS_ID = '1' and ACCOUNT_TYPE_ID = '1'\n" + //
                                        "")) {

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                response.getActiveUsers().add(new UsersForAdmin(rs.getString("ID"), rs.getString("USERNAME"),
                        rs.getString("PASSWORD_CLEAR"), rs.getString("PASSWORD"), rs.getString("ACCOUNT_TYPE_ID"),
                        rs.getString("SECURITY_Q"), rs.getString("SECURITY_A"), rs.getString("FIRST_NAME"),
                        rs.getString("FIRST_NAME"), rs.getString("SEX"), rs.getString("CONTACT_PHONE"),
                        rs.getString("EMAIL"), rs.getString("PROFILE_PICTURE"), rs.getString("CREDIT_CARD_NO"),
                        rs.getString("STATUS_ID"), rs.getString("INSERT_TIME"), rs.getString("ADDRESS")));
            }

        }
        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "SELECT \n" + //
                                        "    ID,\n" + //
                                        "    USERNAME,\n" + //
                                        "    PASSWORD_CLEAR,\n" + //
                                        "    PASSWORD,\n" + //
                                        "    ACCOUNT_TYPE_ID,\n" + //
                                        "    SECURITY_Q,\n" + //
                                        "    SECURITY_A,\n" + //
                                        "    FIRST_NAME,\n" + //
                                        "    LAST_NAME,\n" + //
                                        "    SEX,\n" + //
                                        "    CONTACT_PHONE,\n" + //
                                        "    EMAIL,\n" + //
                                        "    PROFILE_PICTURE,\n" + //
                                        "    CREDIT_CARD_NO,\n" + //
                                        "    STATUS_ID,\n" + //
                                        "    INSERT_TIME,\n" + //
                                        "    ADDRESS\n" + //
                                        "FROM users\n" + //
                                        "where STATUS_ID = '1' and ACCOUNT_TYPE_ID = '2'\n" + //
                                        "")) {

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                response.getWaiters().add(new UsersForAdmin(rs.getString("ID"), rs.getString("USERNAME"),
                        rs.getString("PASSWORD_CLEAR"), rs.getString("PASSWORD"), rs.getString("ACCOUNT_TYPE_ID"),
                        rs.getString("SECURITY_Q"), rs.getString("SECURITY_A"), rs.getString("FIRST_NAME"),
                        rs.getString("FIRST_NAME"), rs.getString("SEX"), rs.getString("CONTACT_PHONE"),
                        rs.getString("EMAIL"), rs.getString("PROFILE_PICTURE"), rs.getString("CREDIT_CARD_NO"),
                        rs.getString("STATUS_ID"), rs.getString("INSERT_TIME"), rs.getString("ADDRESS")));
            }

        }

        try (Connection conn = DB.source().getConnection();
                PreparedStatement stm = conn
                        .prepareStatement(
                                "SELECT \n" + //
                                        "    ID,\n" + //
                                        "    NAME,\n" + //
                                        "    ADDRESS,\n" + //
                                        "    TYPE,\n" + //
                                        "    PHONE_NUMBER,\n" + //
                                        "    EMBEDDED_MAP,\n" + //
                                        "    MAX_NUMBER_OF_PEOPLE,\n" + //
                                        "    OPENS_AT,\n" + //
                                        "    CLOSES_AT,\n" + //
                                        "    LAYOUT,\n" + //
                                        "    TABLES_TAKEN," + //
                                        "    SHORT_DESCRIPTION\n" + //
                                        "FROM restaurant")) {

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("ID");
                List<OperatingHours> oprList = new ArrayList<>();
                try (Connection conn4 = DB.source().getConnection();
                        PreparedStatement stm4 = conn4
                                .prepareStatement(
                                        "select oh.id,oh.restaurant_id,oh.day_of_week,oh.opens_at,oh.closes_at\n" + //
                                                "from restaurant r, operating_hours oh\n" + //
                                                "where r.id = oh.RESTAURANT_ID and r.id = ?")) {

                    stm4.setString(1, id);
                    ResultSet rs4 = stm4.executeQuery();

                    while (rs4.next()) {
                        OperatingHours opr = new OperatingHours(rs4.getString("ID"), rs4.getString("restaurant_id"),
                                rs4.getString("day_of_week"), rs4.getString("opens_at"),
                                rs4.getString("closes_at"));
                        oprList.add(opr);
                    }

                }

                response.getRestaurants()
                        .add(new RestaurantsForAdmin(rs.getString("ID"), rs.getString("NAME"), rs.getString("ADDRESS"),
                                rs.getString("TYPE"), rs.getString("PHONE_NUMBER"), rs.getString("EMBEDDED_MAP"),
                                rs.getString("MAX_NUMBER_OF_PEOPLE"), rs.getString("OPENS_AT"),
                                rs.getString("CLOSES_AT"), rs.getString("LAYOUT"),
                                rs.getString("TABLES_TAKEN"), rs.getString("SHORT_DESCRIPTION"), oprList));

            }

        }
        return response;
    }

    @PostMapping("update-user-status")
    public BasicResponse updateUserStatus(@RequestBody UpdateUserStatus entity) throws SQLException {

        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "update users set status_id = ? where id = ?")) {

            stm2.setString(1, entity.getStatus());
            stm2.setString(2, entity.getUserId());
            System.err.println(stm2);
            stm2.executeUpdate();

        }
        return new BasicResponse();
    }

    @PostMapping("edit-user-data")
    public BasicResponse postMethodName(@RequestBody UsersForAdmin entity) throws SQLException {

        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "UPDATE users\n" + //
                                        "SET \n" + //
                                        "    USERNAME = ?,\n" + //
                                        "    PASSWORD_CLEAR = ?,\n" + //
                                        "    PASSWORD = ?,\n" + //
                                        "    ACCOUNT_TYPE_ID = ?,\n" + //
                                        "    SECURITY_Q = ?,\n" + //
                                        "    SECURITY_A = ?,\n" + //
                                        "    FIRST_NAME = ?,\n" + //
                                        "    LAST_NAME = ?,\n" + //
                                        "    SEX = ?,\n" + //
                                        "    CONTACT_PHONE = ?,\n" + //
                                        "    EMAIL = ?,\n" + //
                                        "    PROFILE_PICTURE = ?,\n" + //
                                        "    CREDIT_CARD_NO = ?,\n" + //
                                        "    ADDRESS = ?\n" + //
                                        "WHERE\n" + //
                                        "    id = ?;")) {

            stm2.setString(1, entity.getUsername());
            stm2.setString(2, entity.getPasswordClear());
            stm2.setString(3, entity.getPassword());
            stm2.setString(4, entity.getAccountTypeId());
            stm2.setString(5, entity.getSecurityQ());
            stm2.setString(6, entity.getSecurityA());
            stm2.setString(7, entity.getFirstName());
            stm2.setString(8, entity.getLastName());
            stm2.setString(9, entity.getSex());
            stm2.setString(10, entity.getContactPhone());
            stm2.setString(11, entity.getEmail());
            stm2.setString(12, entity.getProfilePicture());
            stm2.setString(13, entity.getCreditCardNo());
            stm2.setString(14, entity.getAddress());
            stm2.setString(15, entity.getId());
            System.err.println(stm2);
            stm2.executeUpdate();

        }
        return null;
    }

    @PostMapping("edit-restaurant-admin")
    public String postMethodName(@RequestBody RestaurantsForAdmin entity) throws SQLException {
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "update restaurant set layout = ? where id = ?")) {

            stm2.setString(1, entity.getLayout());

            stm2.setString(2, entity.getId());
            System.err.println(stm2);
            stm2.executeUpdate();

        }

        for (OperatingHours oprH : entity.getOperationgHours()) {
            try (Connection conn2 = DB.source().getConnection();
                    PreparedStatement stm2 = conn2
                            .prepareStatement(
                                    "update operating_hours set opens_at = ?, closes_at = ? where id = ?")) {

                stm2.setString(1, oprH.getOpensAt());
                stm2.setString(2, oprH.getClosesAt());
                stm2.setString(3, oprH.getId());
                System.err.println(stm2);
                stm2.executeUpdate();

            }
        }

        return null;
    }

    @PostMapping("insert-restaurant")
    public BasicResponse postMethodName(@RequestBody InsertNewRestaurant entity) throws SQLException {
        int rowsInserted = 0;
        ResultSet generatedKeys = null;
        long id = 0;
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "insert into restaurant(NAME,ADDRESS,TYPE,PHONE_NUMBER,EMBEDDED_MAP,MAX_NUMBER_OF_PEOPLE,OPENS_AT,CLOSES_AT,SHORT_DESCRIPTION,TABLES_TAKEN) VALUES (?,?,?,?,?,?,?,?,?,'{}')",
                                Statement.RETURN_GENERATED_KEYS)) {

            stm2.setString(1, entity.getName());
            stm2.setString(2, entity.getAddress());
            stm2.setString(3, entity.getType());
            stm2.setString(4, entity.getPhoneNumber());
            stm2.setString(5, entity.getEmbeddedMap());
            stm2.setString(6, entity.getMaxNumberOfPeople());
            stm2.setString(7, entity.getOpensAt());
            stm2.setString(8, entity.getClosesAt());
            stm2.setString(9, entity.getShortDescription());
            System.err.println(stm2);
            rowsInserted = stm2.executeUpdate();
            generatedKeys = stm2.getGeneratedKeys();
            generatedKeys.next();
            id = generatedKeys.getLong(1);
        }
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "INSERT INTO operating_hours (restaurant_id, DAY_OF_WEEK, OPENS_AT, CLOSES_AT) VALUES (?, 'Monday', '00:00:00', '00:00:00');\r\n")) {
            stm2.setString(1, id + "");
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "INSERT INTO operating_hours (restaurant_id, DAY_OF_WEEK, OPENS_AT, CLOSES_AT) VALUES (?, 'Tuesday', '00:00:00', '00:00:00');\r\n")) {
            stm2.setString(1, id + "");
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "INSERT INTO operating_hours (restaurant_id, DAY_OF_WEEK, OPENS_AT, CLOSES_AT) VALUES (?, 'Wednesday', '00:00:00', '00:00:00');\r\n")) {
            stm2.setString(1, id + "");
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "INSERT INTO operating_hours (restaurant_id, DAY_OF_WEEK, OPENS_AT, CLOSES_AT) VALUES (?, 'Thursday', '00:00:00', '00:00:00');\r\n")) {
            stm2.setString(1, id + "");
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "INSERT INTO operating_hours (restaurant_id, DAY_OF_WEEK, OPENS_AT, CLOSES_AT) VALUES (?, 'Friday', '00:00:00', '00:00:00');\r\n")) {
            stm2.setString(1, id + "");
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "INSERT INTO operating_hours (restaurant_id, DAY_OF_WEEK, OPENS_AT, CLOSES_AT) VALUES (?, 'Saturday', '00:00:00', '00:00:00');\r\n")) {
            stm2.setString(1, id + "");
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "INSERT INTO operating_hours (restaurant_id, DAY_OF_WEEK, OPENS_AT, CLOSES_AT) VALUES (?, 'Sunday', '00:00:00', '00:00:00');\r\n")) {
            stm2.setString(1, id + "");
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        return null;
    }

    @PostMapping("link-waiter-restaurant")
    public BasicResponse postMethodName(@RequestBody LinkWaiterRestaurant entity) throws SQLException {
        try (Connection conn2 = DB.source().getConnection();
                PreparedStatement stm2 = conn2
                        .prepareStatement(
                                "insert into waiter_restaurant(waiter_id,restaurant_id) values(?,?)")) {
            stm2.setString(1, entity.getWaiterId());
            stm2.setString(2, entity.getRestaurantId());
            stm2.executeUpdate();
            System.err.println(stm2);
        }
        return null;
    }

}