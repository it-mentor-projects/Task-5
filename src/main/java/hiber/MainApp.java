package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        Car car1 = new Car("bmw", 5);
        saveUser(userService, user1, car1);

        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        Car car2 = new Car("volkswagen", 2);
        saveUser(userService, user2, car2);

        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        Car car3 = new Car("mercedes", 6);
        saveUser(userService, user3, car3);

        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        Car car4 = new Car("audi", 7);
        saveUser(userService, user4, car4);

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
        }
        System.out.println("User by car:");
        userService.findByModelAndSeries("mercedes", 63)
                .ifPresent(System.out::println);

        context.close();
    }

    private static void saveUser(UserService userService, User user, Car car) {
        user.setCar(car);
        car.setUser(user);
        userService.add(user);
    }
}
