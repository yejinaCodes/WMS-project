# Introduction 

Warehouse management system manages the entire process within the Supply Chain Management System that are related to warehousing. WMS enhances efficiency in logistics management.

## Objective 🔵

The objective of this project is to understand warehouse management system and develop core operations of wms.
The key operations are as follows:

1. User registration and login system
3. Purchase order & Stock request system
5. Release request system
6. Stock management
7. Customer service center


## Package Structure 🟠

The packaging structure has been designed to help visualize the entire structure and distinguish each function within the development phase.

common - includes methods, enum types that are used across multiple function.

config - defines properties and DB connection.

controller - works as user interaction phase and prints user screen.

dao - data access object. Takes in charge of DB interaction. Retrieval, insertion, update, and delete.

dto - data transfer object. Defines the object parameters.

exception - includes user defined exception.

library - includes script which replaces println in Java and enhances readability.

service - prosecutes business logic of the operation.
