📌 Project Overview

The Bharat Gas Agency Management System is a Java-based console application that simulates the working process of a gas distribution agency.
It manages customer information, gas bookings, delivery operations, OTP verification, and invoice generation.

The system demonstrates the use of Object-Oriented Programming (OOP) concepts such as inheritance, interfaces, method overriding, and encapsulation.

This project was developed for educational purposes to understand real-world system simulation using Java.

                                              🔗 Features

Customer registration and details management

Gas connection with configurable cylinder count

Gas booking and delivery tracking

OTP-based delivery verification

Automatic refund calculation for late delivery

Monthly cylinder delivery statistics

Delivery status report (Booked / Delivered / Cancelled / Pending)

Invoice generation for delivered cylinders

Search delivery details by delivery person

Identification of single-cylinder customers

                                           🏗️ Object-Oriented Concepts Used

This project demonstrates several Java OOP principles:

Inheritance

GasConnection extends Customer

Booking extends GasConnection

Delivery extends Booking

Interface

gasAgency interface provides agency constants and default methods.

Method Overriding

verifyOtp() and getDates() overridden in Delivery.

Encapsulation

Cylinder count managed through getter methods.

                                               ⚙️ How the System Works

Customer details are created with gas connection information.

The system asks for:

Last booking date

Booking date

Delivery date

Booking validation checks whether booking is allowed based on cylinder type.

OTP verification confirms the delivery.

If delivery takes more than 7 days, a refund is applied.

The system then generates:

Cylinder delivery statistics

Late delivery reports

Delivery reports

Customer invoices

