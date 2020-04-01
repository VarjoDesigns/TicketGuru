
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
    (password, username, name, userGroup_ID, active)
VALUES
    ('$2a$10$/..0qbQN09s20ZVao53j0..hr2dgkS52zVn68b0ZlGcZBzczkoH.y', 'pepe', 'pepe', 1, 1);
INSERT INTO Users
    (password, username, name, userGroup_ID, active)
VALUES
    ('$2a$10$K.vSuwrxuG7arNO7nGkAyuPs0Op4JCDxd7hdhiwpF/egYpePZWYay', 'john', 'john', 2, 1);
INSERT INTO Users
    (password, username, name, userGroup_ID, active)
VALUES
    ('$2a$10$tG3a9iRmIpPH3Hkj/EwLYetL8i/A6jBJIF4OQZS.UPV14sOmQB9Nq', 'kuningas', 'kuningas', 3, 1);


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
    INSERT INTO Events
    (name, eventType_ID, dateTime, eventOrganizer_ID, venue_ID, ticketCapacity, ageLimit_ID, info)
VALUES
    (
        'Olut festarit', 1, '2020-04-01 09:15:00', 1, 1, 700, 2, 'Paljon erilaisia oluita tarjolla'
    );
     INSERT INTO Events
    (name, eventType_ID, dateTime, eventOrganizer_ID, venue_ID, ticketCapacity, ageLimit_ID, info)
VALUES
    (
        'Black Metal festarit', 1, '2020-04-04 18:30:00', 1, 1, 666, 2, 'Pelkkää bläkkistä. Tiukkaa tykittelyä.'
    );
     INSERT INTO Events
    (name, eventType_ID, dateTime, eventOrganizer_ID, venue_ID, ticketCapacity, ageLimit_ID, info)
VALUES
    (
        'Lappeenranta Kiljufest 2020', 1, '2020-04-13 07:00:00', 1, 1, 15, 2, 'Juo kiljua ja piereskele.'
    );
     INSERT INTO Events
    (name, eventType_ID, dateTime, eventOrganizer_ID, venue_ID, ticketCapacity, ageLimit_ID, info)
VALUES
    (
        'Postimekkijamboree', 1, '2020-04-17 10:00:00', 1, 1, 15, 2, 'Liimaile postimerkkejä'
    );
     INSERT INTO Events
    (name, eventType_ID, dateTime, eventOrganizer_ID, venue_ID, ticketCapacity, ageLimit_ID, info)
VALUES
    (
        'Mökkitikan SM:t', 1, '2020-04-20 09:030:00', 1, 1, 15, 2, 'Heitä tikkaa pikkusievässä'
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

INSERT INTO TicketStatuses (name) VALUES ('Voimassa');

INSERT INTO TicketStatuses (name) VALUES ('Käytetty');

INSERT INTO TicketStatuses (name) VALUES ('Peruttu');

INSERT INTO TicketStatuses (name) VALUES ('Maksettu');

INSERT INTO TicketStatuses (name) VALUES ('Hyväksymätön');

INSERT INTO TicketStatuses (name) VALUES ('Epäonnistui');

INSERT INTO TicketStatuses (name) VALUES ('Vylätty');

-- EVENTTICKETS

INSERT INTO EventTickets
    (event_ID, ticketType_ID, ticketCount, price)
VALUES
    (
        1, 2, 1000, 20
    );

-- SALEEVENTS

INSERT INTO SaleEvents (dateTime, user_ID) VALUES ('2020-03-03 10:00:00', 3);

-- SALEROWS

INSERT INTO SaleRows (discount, saleEvent_ID) VALUES (0, 1);

-- TICKETS

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID, saleRow_ID) VALUES ('Testi_1', 1, 1, 1);

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID, saleRow_ID) VALUES ('Testi_2', 1, 1, 1);

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID, saleRow_ID) VALUES ('Testi_3', 1, 2, 1);

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID, saleRow_ID) VALUES ('Testi_4', 1, 3, 1);

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID, saleRow_ID) VALUES ('Testi_5', 1, 4, 1);

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID, saleRow_ID) VALUES ('Testi_6', 1, 5, 1);

INSERT INTO Tickets (checkSum, eventTicket_ID, ticketStatus_ID, saleRow_ID) VALUES ('Testi_7', 1, 6, 1);
