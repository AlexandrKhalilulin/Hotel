CREATE TABLE PUBLIC.Room
(
  ID             INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  Beds_Count     INT                            NOT NULL,
  Apartment_Type VARCHAR(255)                   NOT NULL,
  Daily_cost     INT                            NOT NULL
);