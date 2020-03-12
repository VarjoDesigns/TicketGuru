
-- USER GROUPS

INSERT INTO UserGroups
    (name)
VALUES
    ('user');
INSERT INTO UserGroups
    (name
    )
VALUES
    ('admin');
INSERT INTO UserGroups
    (name
    )
VALUES
    ('boss');

-- USERS

INSERT INTO Users
    (password, name, userGroup_ID, active)
VALUES
    ('$2a$10$/..0qbQN09s20ZVao53j0..hr2dgkS52zVn68b0ZlGcZBzczkoH.y', 'pepe', 1, 1);
INSERT INTO Users
    (password, name, userGroup_ID, active)
VALUES
    ('$2a$10$K.vSuwrxuG7arNO7nGkAyuPs0Op4JCDxd7hdhiwpF/egYpePZWYay', 'john', 2, 1);
INSERT INTO Users
    (password, name, userGroup_ID, active)
VALUES
    ('$2a$10$tG3a9iRmIpPH3Hkj/EwLYetL8i/A6jBJIF4OQZS.UPV14sOmQB9Nq', 'kuningas', 3, 1);
INSERT INTO Users
    (password, name, userGroup_ID, active)
VALUES
    ('keke', 'keke', 1, 0);


-- EVENTTYPES

INSERT INTO EventTypes
    (name, info)
VALUES
    ('Teatteri', 'Silkkaa teatteria'
);

-- AGELIMITS

INSERT INTO AgeLimits
    (name, specifier)
VALUES
    ('K7', 'Tapahtuma ei sovellu alle 7-vuotiaille');

INSERT INTO AgeLimits
    (name, specifier)
VALUES
    ('K13', 'Tapahtuma ei sovellu alle 13-vuotiaille');
    
INSERT INTO AgeLimits
    (name, specifier)
VALUES
    ('K16', 'Tapahtuma ei sovellu alle 16-vuotiaille');
    
INSERT INTO AgeLimits
    (name, specifier)
VALUES
    ('K18', 'Tapahtuma ei sovellu alle 18-vuotiaille');
    
INSERT INTO AgeLimits
    (name, specifier)
VALUES
    ('S', 'Ikärajaton tapahtuma, sopii kaikenikäisille');

-- POSTCODES

INSERT INTO Postcodes
    (postcode, city, country)
VALUES
    (00002, 'Helsinki', 'Finland');
INSERT INTO Postcodes
    (postcode, city, country
    )
VALUES
    (00100, 'Helsinki', 'Finland');
INSERT INTO Postcodes
    (postcode, city, country
    )
VALUES
    (00130, 'Helsinki', 'Finland'
);
INSERT INTO Postcodes
    (postcode, city, country
    )
VALUES
    (00140, 'Helsinki', 'Finland'
);
INSERT INTO Postcodes
    (postcode, city, country
    )
VALUES
    (00150, 'Helsinki', 'Finland'
);
INSERT INTO Postcodes
    (postcode, city, country
    )
VALUES
    (00160, 'Helsinki', 'Finland'
);

-- VENUES

INSERT INTO Venues
    (name, streetAddress, postcode_ID, tel, email, www, contactPerson)
VALUES
    ('Helsingin Teatteri', 'Kekkosenkatu 3', 3, '09 1234566', 'teatteri@teatteri.fi', 'www.helsinginteatteri.com', 'John Wayne');

-- EVENTORGANIZERS

INSERT INTO EventOrganizers
    (name, streetAddress, postcode_ID, tel, email, www, contactPerson)
VALUES
    ('GREAT EVENTS OY', 'Tapahtumakatu 16 a 78', 4, '09 7865566', 'great@events.fi', 'www.greatevents.com', 'Texas Ted');

-- EVENTS

INSERT INTO Events
    (name, eventType_ID, dateTime, eventOrganizer_ID, venue_ID, ticketCapacity, ageLimit_ID, info)
VALUES
    (
        'Koodari Kemut 2020', 1, '2020-03-01 20:00:00', 1, 1, 1500, 2, 'Mika koodaa ja muut kattelee. Kannattaa tulla kauempaakin'
    );

-- TICKETTYPES

INSERT INTO TicketTypes
    (name)
VALUES
    (
        'Aikuinen'
    );
INSERT INTO TicketTypes
    (name)
VALUES
    (
        'Lapsi 7-16v'
    );
    
INSERT INTO TicketTypes
    (name)
VALUES
    (
        'Eläkeläinen'
    );
    
INSERT INTO TicketTypes
    (name)
VALUES
    (
        'Varus- tai siviilipalvelusmies'
    );
    
INSERT INTO TicketTypes
    (name)
VALUES
    (
        'Työtön'
    );

-- TICKETSTATUSES

INSERT INTO TicketStatuses (name) VALUES ('voimassa');

INSERT INTO TicketStatuses (name) VALUES ('mitätöity');

INSERT INTO TicketStatuses (name) VALUES ('peruttu');

INSERT INTO TicketStatuses (name) VALUES ('maksettu');

INSERT INTO TicketStatuses (name) VALUES ('hyväksymätön');

INSERT INTO TicketStatuses (name) VALUES ('epäonnistui');

INSERT INTO TicketStatuses (name) VALUES ('hylätty');

-- EVENTTICKETS

INSERT INTO EventTickets
    (event_ID, ticketType_ID, ticketCount, price)
VALUES
    (
        1, 2, 1000, 20
    );

-- SALEEVENTS

INSERT INTO SaleEvents (dateTime, user_ID) VALUES ('2020-03-03 10:00:00', 3);

-- TICKETS / KAATUU SAATANA!!!

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID) VALUES ('hbfhbvhfbvrhbgeyfgf', 1, 1);

-- SALEROWS / KAATUU SAATANA!!!

INSERT INTO SaleRows (discount, saleEvent_ID, ticket_ID) VALUES (0, 1, 1 );
