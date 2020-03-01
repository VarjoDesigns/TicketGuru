DROP TABLE IF EXISTS AgeLimits;

CREATE TABLE AgeLimits
(
    ageLimit_ID INTEGER NOT NULL
    AUTO_INCREMENT,
  name VARCHAR
    (50) NOT NULL,
  specifier VARCHAR
    (500) NULL,
  PRIMARY KEY
    (ageLimit_ID)
);

    DROP TABLE IF EXISTS EventOrganizers;

    CREATE TABLE EventOrganizers
    (
        eventOrganizer_ID INTEGER NOT NULL
        AUTO_INCREMENT,
  name VARCHAR
        (100) NOT NULL,
  streetAddress VARCHAR
        (150) NULL,
  postcode_ID VARCHAR
        (20) NOT NULL,
  tel VARCHAR
        (25) NULL,
  email VARCHAR
        (150) NULL,
  www VARCHAR
        (250) NULL,
  contactPerson VARCHAR
        (150) NULL,

  PRIMARY KEY
        (eventOrganizer_ID)
);

        DROP TABLE IF EXISTS Events;

        CREATE TABLE Events
        (
            event_ID INTEGER NOT NULL
            AUTO_INCREMENT,
  name VARCHAR
            (250) NOT NULL,
  eventType_ID INTEGER NOT NULL,
  dateTime DATETIME NOT NULL,
  eventOrganizer_ID INTEGER NOT NULL,
  venue_ID INTEGER NOT NULL,
  ticketCapacity INTEGER NOT NULL,
  ageLimit_ID INTEGER NOT NULL,
  Info VARCHAR
            (500) NULL,
  PRIMARY KEY
            (event_ID)
);

            DROP TABLE IF EXISTS EventTickets;

            CREATE TABLE EventTickets
            (
                eventTicket_ID INTEGER NOT NULL
                AUTO_INCREMENT,
  event_ID INTEGER NOT NULL,
  ticketType_ID INTEGER NOT NULL,
  ticketCount INTEGER NULL,
  price DECIMAL
                (5,2) NULL,
  PRIMARY KEY
                (eventTicket_ID)
);

                DROP TABLE IF EXISTS EventTypes;

                CREATE TABLE EventTypes
                (
                    eventType_ID INTEGER NOT NULL
                    AUTO_INCREMENT,
  name VARCHAR
                    (100) NOT NULL,
  info VARCHAR
                    (500) NULL,
  PRIMARY KEY
                    (eventType_ID)
  );

                    DROP TABLE IF EXISTS Postcodes;

                    CREATE TABLE Postcodes
                    (
                        postcode_ID VARCHAR(20) NOT NULL,
                        city VARCHAR(100) NOT NULL,
                        country VARCHAR(100) NOT NULL,
                        PRIMARY KEY (postcode_ID)
                    );

                    DROP TABLE IF EXISTS SaleEvents;

                    CREATE TABLE SaleEvents
                    (
                        saleEvent_ID INTEGER NOT NULL
                        AUTO_INCREMENT,
  dateTime DATETIME NOT NULL,
  user_ID INT NOT NULL,
  PRIMARY KEY
                        (saleEvent_ID)
);

                        DROP TABLE IF EXISTS SaleRows;

                        CREATE TABLE SaleRows
                        (
                            saleRow_ID INTEGER NOT NULL
                            AUTO_INCREMENT,
  saleEvent_ID INTEGER NOT NULL,
  ticket_ID INTEGER NOT NULL,
  discount INTEGER NULL,
  PRIMARY KEY
                            (saleRow_ID)
);

                            DROP TABLE IF EXISTS Tickets;

                            CREATE TABLE Tickets
                            (
                                ticket_ID INTEGER NOT NULL
                                AUTO_INCREMENT,
  eventTicket_ID INTEGER NOT NULL,
  ticketStatus_ID INTEGER NOT NULL,
  checkSum VARCHAR
                                (50) NOT NULL,
  PRIMARY KEY
                                (ticket_ID)
);

                                DROP TABLE IF EXISTS TicketStatuses;

                                CREATE TABLE TicketStatuses
                                (
                                    ticketStatus_ID INTEGER NOT NULL
                                    AUTO_INCREMENT,
  name VARCHAR
                                    (50) NULL,
  PRIMARY KEY
                                    (ticketStatus_ID)
  );

                                    DROP TABLE IF EXISTS TicketTypes;

                                    CREATE TABLE TicketTypes
                                    (
                                        ticketType_ID INTEGER NOT NULL
                                        AUTO_INCREMENT,
  name VARCHAR
                                        (100) NOT NULL,
  PRIMARY KEY
                                        (ticketType_ID)
  );

                                        DROP TABLE IF EXISTS UserGroups;

                                        CREATE TABLE UserGroups
                                        (
                                            userGroup_ID INTEGER NOT NULL
                                            AUTO_INCREMENT,
  name VARCHAR
                                            (100) NOT NULL,
  PRIMARY KEY
                                            (userGroup_ID)
  );

                                            DROP TABLE IF EXISTS Users;

                                            CREATE TABLE Users
                                            (
                                                user_ID INTEGER NOT NULL
                                                AUTO_INCREMENT,
  password VARCHAR
                                                (100) NOT NULL,
  name VARCHAR

                                                (50) NOT NULL,
                                                
userGroup_ID INTEGER
NOT NULL,
  PRIMARY KEY
                                                (user_ID)
);

                                                DROP TABLE IF EXISTS Venues;

                                                CREATE TABLE Venues
                                                (
                                                    Venue_ID INTEGER NOT NULL
                                                    AUTO_INCREMENT,
  name VARCHAR
                                                    (100) NOT NULL,
  streetAddress VARCHAR
                                                    (150) NOT NULL,
  postcode_ID VARCHAR
                                                    (20) NOT NULL,
  tel VARCHAR
                                                    (25) NOT NULL,
  email VARCHAR
                                                    (150) NOT NULL,
  www VARCHAR
                                                    (250) NULL,
  contactPerson VARCHAR
                                                    (150) NULL,
  PRIMARY KEY
                                                    (Venue_ID)
);

