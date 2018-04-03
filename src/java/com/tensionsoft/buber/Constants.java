package com.tensionsoft.buber;

import com.tensionsoft.buber.persistence.model.Car;
import com.tensionsoft.buber.persistence.model.Card;
import com.tensionsoft.buber.persistence.model.Location;
import com.tensionsoft.buber.persistence.model.Order;
import com.tensionsoft.buber.persistence.model.User;


import java.sql.Date;

public class Constants {
    public static Location fakeLocation0 = new Location();
    public static Location fakeLocation1 = new Location();
    public static Location fakeLocation2 = new Location();
    public static Location fakeLocation3 = new Location();
    public static Location fakeLocation4 = new Location();
    public static Location fakeLocation5 = new Location();

    public static User fakeKatya = new User();
    public static User fakeYan = new User();
    public static User fakeRoma = new User();

    public static Order fakeOrder0 = new Order();
    public static Order fakeOrder1 = new Order();
    public static Order fakeOrder2 = new Order();

    public static Car fakeCar0 = new Car();
    public static Car fakeCar1 = new Car();
    public static Car fakeCar2 = new Car();

    public static Card fakeCard0 = new Card();
    public static Card fakeCard1 = new Card();
    public static Card fakeCard2 = new Card();

    //Location
    static {
        fakeLocation0.setId(0L);
        fakeLocation0.setLatitude("53.123");
        fakeLocation0.setLongitude("54.213");

        fakeLocation1.setId(1L);
        fakeLocation1.setLatitude("50.123");
        fakeLocation1.setLongitude("54.13");

        fakeLocation2.setId(2L);
        fakeLocation2.setLatitude("49.123");
        fakeLocation2.setLongitude("52.13");

        fakeLocation3.setId(3L);
        fakeLocation3.setLatitude("51.13");
        fakeLocation3.setLongitude("44.12");

        fakeLocation4.setId(4L);
        fakeLocation4.setLatitude("31.42");
        fakeLocation4.setLongitude("31.10");

        fakeLocation5.setId(5L);
        fakeLocation5.setLatitude("40.40");
        fakeLocation5.setLongitude("40.50");
    }

    //User
    static {
        fakeKatya.setId(0L);
        fakeKatya.setUsername("wcrtch");
        fakeKatya.setName("Katya");
        fakeKatya.setSurname("Nesterova");
        fakeKatya.setLastname("Koteikina");
        fakeKatya.setEmail("wkrja@gmail.com");
        fakeKatya.setReputation(0);
        fakeKatya.setPhoneNumber("1234");
        fakeKatya.setStatus(User.Status.INACTIVE);
        fakeKatya.setTripsNumber(12);
        fakeKatya.setLocation(fakeLocation0);
        fakeKatya.setIsMuted(false);
        fakeKatya.setIsOnline(true);

        fakeYan.setId(1L);
        fakeYan.setUsername("galadopter");
        fakeYan.setName("Yan");
        fakeYan.setSurname("Schneider");
        fakeYan.setLastname("Alexandrovich");
        fakeYan.setEmail("goliidopter@gmail.com");
        fakeYan.setReputation(1000);
        fakeYan.setPhoneNumber("4321");
        fakeYan.setStatus(User.Status.INACTIVE);
        fakeYan.setTripsNumber(13);
        fakeKatya.setLocation(fakeLocation1);
        fakeYan.setIsMuted(false);
        fakeYan.setIsOnline(true);

        fakeRoma.setId(2L);
        fakeRoma.setUsername("lateroad");
        fakeRoma.setName("Roman");
        fakeRoma.setSurname("Pozdnyakov");
        fakeRoma.setLastname("Yurievich");
        fakeRoma.setEmail("lateroad@gmail.com");
        fakeRoma.setIsOnline(true);
    }

    //Order
    static {
        fakeOrder0.setId(0L);
        fakeOrder0.setClientLogin(fakeKatya.getUsername());
        fakeOrder0.setDriverLogin("galadopter");
        fakeOrder0.setOrigin(fakeKatya.getLocation());
        fakeOrder0.setDestination(fakeLocation2);
        fakeOrder0.setDate(new Date(System.currentTimeMillis()));
        fakeOrder0.setMoney("90");
        fakeOrder0.setStatus(Order.Status.DONE);


        fakeOrder1.setId(1L);
        fakeOrder1.setClientLogin(fakeKatya.getUsername());
        fakeOrder1.setDriverLogin("galadopter");
        fakeOrder1.setOrigin(fakeLocation2);
        fakeOrder1.setDestination(fakeLocation4);
        fakeOrder1.setDate(new Date(System.currentTimeMillis()));
        fakeOrder1.setMoney("100");
        fakeOrder1.setStatus(Order.Status.DONE);

        fakeOrder2.setId(2L);
        fakeOrder2.setClientLogin(fakeKatya.getUsername());
        fakeOrder2.setDriverLogin("galadopter");
        fakeOrder2.setOrigin(fakeLocation4);
        fakeOrder2.setDestination(fakeLocation5);
        fakeOrder2.setDate(new Date(System.currentTimeMillis()));
        fakeOrder2.setMoney("20");
        fakeOrder2.setStatus(Order.Status.ACCEPTED);
    }

    //Car
    static {
        fakeCar0.setId(0L);
        fakeCar0.setDriverLogin(fakeYan.getUsername());
        fakeCar0.setCarNumber("7777-7");

        fakeCar1.setId(1L);
        fakeCar1.setDriverLogin(fakeYan.getUsername());
        fakeCar1.setCarNumber("0000-87");

        fakeCar2.setId(2L);
        fakeCar2.setDriverLogin(fakeYan.getUsername());
        fakeCar2.setCarNumber("pidor-7");
    }

    //Card
    static {
        fakeCard0.setId(0L);
        fakeCard0.setLogin(fakeYan.getUsername());
        fakeCard0.setHashNumber("67aads21edsa");
        fakeCard0.setLastDigits("3245");

        fakeCard1.setId(1L);
        fakeCard1.setLogin(fakeYan.getUsername());
        fakeCard1.setHashNumber("6eqd82qhdkaj");
        fakeCard1.setLastDigits("0021");

        fakeCard2.setId(2L);
        fakeCard2.setLogin(fakeYan.getUsername());
        fakeCard2.setHashNumber("9aijdihwq8sd");
        fakeCard2.setLastDigits("4322");
    }
}
