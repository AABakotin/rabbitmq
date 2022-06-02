package com.flamexander.rabbitmq.console.producer;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class Producer {
    private static final String EXCHANGER_NAME = "MyExchanger";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGER_NAME, BuiltinExchangeType.TOPIC);

            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            String[] temp = msg.split(" ", 2);
            String routingKey = temp[0];
            String message = temp[1];

            channel.basicPublish(EXCHANGER_NAME, routingKey, null, message.getBytes());
            System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
        }
    }


}
