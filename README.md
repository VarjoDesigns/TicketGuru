## TicketGuru - lipunmyyntijärjestelmä

Projektin tarkoituksena on tuottaa lipunmyyntijärjestelmä TicketGuru –nimiselle toimitsijalle. Toimitsija toimii projektin aloittaessa kivijalkaliike-periaatteella, mutta toimitsijan tarkoituksena on jatkokehittää ja laajentaa palveluitaan myös verkkokaupan suuntaan, jonka myötä asiakkaat voivat itse ostaa ja tulostaa lippuja suoraan järjestelmän verkkokaupasta.

Toimitsijan tulee pystyä määrittelemään järjestelmään tapahtumat tietoineen (tapahtuma, lippukapasiteetti, lipputyypit, aika ja paikka, jne.), joihin lippuja myydään. Liput tulee pystyä tulostamaan järjestelmästä asiakkaalle myynnin yhteydessä. Lisäksi, ennakkomyynnin loputtua, jäljellä olevien lippujen tulostus, ovella myynti sekä lippujen tarkastaminen ovella lipulla olevan koodin perusteella tulee mahdollistaa järjestelmän kautta. Järjestelmästä on myös pystyttävä myyntiraportti halutusta tilaisuudesta ja tarkempi myyntitapahtuma listaus samaisen tapahtuman myyntitapahtumista.

TicketGurun tilaaman lipunmyyntijärjestelmän tarkoituksena on koota tiedot järjestettävistä tilaisuuksista (tilaisuuden laji, paikka, ikärajat, lippuryhmät, jne.) yhden ja saman tietojärjestelmän sisälle, joka mahdollistaa lippumyynnin helposti.

Lipunmyyntijärjestelmä on tarkoitettu aluksi käytettäväksi TicketGurun myyntipisteessä TicketGurun työntekijöiden toimesta, myöhemmin tarkoitus laajentaa käytettäväksi myös asiakkaiden toimesta verkkokaupan muodossa.

Toteutus- ja toimintaympäristö:

- Java

- SpringBoot

- Thymeleaf / React

- MariaDB

Järjestelmä on tarkoitettu käytettäväksi selaimen kautta päätteellä, ja sen on tarkoitus olla selainriippumaton.

## Järjestelmän määrittely

- TicketGuru-järjestelmän käyttäjinä ovat pääasiassa lippupisteiden jälleenmyyjät sekä lippupisteiden myyntikoordinaattorit: jälleenmyyjät toimivat kivijalkaliikkeessä myynnin puolella, myyntikoordinaattorit puolestaan toimivat linkkinä tapahtumien järjestäjiin, syöttäen tapahtumat ja niiden tiedot järjestelmään. Järjestelmän tarjoamat raportit ovat myös tärkeitä myyntikoordinaattoreille.

* [User case diagram](https://github.com/marko-airisto/TicketGuru/blob/master/Usercase_RBMK_05022020.pdf)

### Käyttäjätarinat:

- Myyjänä haluan, että saan esille tapahtumien listauksen, jotta näen koko valikoiman

- Myyjänä haluan, että saan tapahtumien listan järjestetty eri tavalla, jotta saan valita tarvittavan

- Myyjänä haluan, että saan esille valitun tapahtuman tiedot, jotta voin selvittää myyntiehdot

- Myyjänä haluan, että saan luoda uuden myyntitapahtuman (Osto), jotta voin aloittaa asiakaspalvelun

- Myyjänä haluan, että saan lisätä tapahtumalle eri tapahtumien liput, jotta valmistan asiakkaan ostoskorin

- Myyjänä haluan, että pystyn muokkaamaan ja poistamaan tapahtumissa olevat liput, jotta kykenen korjaamaan asiakkaan ostoskorin

- Myyjänä haluan, että pystyn siirtymään helposti yhdestä napista takaisin edelliseen näkymään.

- Myyjänä haluan, että valmistan (tallennan, vahvistan) myyntitapahtuman asiakkaan ostoskorista, jotta suoritan maksun ja tulostan liput

- Myyjänä haluan, että jo olleet tapahtumat eivät näy myyntinäkymässä, mutta voin silti tarvittaessa etsiä myös vanhoja tapahtumia.

- Asiakkaana haluan, että saan maksetut liput tulostettuna myyjältä, jotka esitän tarkastajalle

- Tarkastajana haluan, että liput voidaan yksinkertaisesti ja yksiselitteisesti tarkista tapahtumaan sisäänpääsyä varten

- Myyjänä haluan, että saan ostotapahtuman peruttua, jos ostaja peruu kaupan, tai myyjänä teen virheen.

- Myyntikoordinaattorina haluan, että saan tapahtumia tallennettu järjestelmään, jotta lippuja ko. tapahtumiin voidaan myydä jälleenmyyntipisteessä

- Myyntikoordinaattorina haluan, että myyntipisteissä ei voida muuttaa tapahtumien tietoja, jottei myyntiprosessi keskeydy.

- Myyntikoordinaattorina haluan, että pystyn syöttämään tietoja massana (excel, csv), jottei jokaista tapahtumaa pidä syöttää rivi kerrallaan.

- Myyntikoordinaattorina haluan, että saada järjestelmästä myyntiraportin, josta näen haluamani tapahtuman lipputyyppien myyntimäärät ja summat sekä yhteisumman.

- Myyntikoordinaattorina haluan, että saada järjestelmästä myyntiraportista listauksen kaikista myyntitapahtumista (sis. timestamp, myyntitapahtuma nro ja summa), jotta näen ko. myyntitapahtuman sisällön.

## Käyttöliittymä

Alkuperäinen UI wireframe dokumentti, josta näkyy perus toiminallisuus.

- [UI Wireframes](https://github.com/marko-airisto/TicketGuru/blob/master/TicketGuru_UI.pdf)

Esimerkillinen landing page sisäänkirjautumisen jälkeen.

- [Homepage](https://github.com/marko-airisto/TicketGuru/blob/master/images/Homepage.png)

Esimerkillinen lipunmyynti näkymä.

- [Ticket Window](https://github.com/marko-airisto/TicketGuru/blob/master/images/ChooseTicket.png)

Esimerkillinen tapahtuman luonti/editointi näkymä.

- [Edit Event](https://github.com/marko-airisto/TicketGuru/blob/master/images/EditTicket.png)

## Tietokanta

- [EER-kaavio tietokannasta](https://github.com/marko-airisto/TicketGuru/blob/master/EER.pdf)

## Tietohakemisto

- [Tietohakemisto](https://github.com/marko-airisto/TicketGuru/blob/master/TicketGuruDB.md)

## REST API dokumentaatio

- [REST API](https://github.com/marko-airisto/TicketGuru/tree/master/documentation/API)

## TÄSTÄ ALASPÄIN TEHTÄVÄ!

## Testaus

Tässä kohdin selvitetään, miten ohjelmiston oikea toiminta varmistetaan
testaamalla projektin aikana: millaisia testauksia tehdään ja missä vaiheessa.
Testauksen tarkemmat sisällöt ja testisuoritusten tulosten raportit kirjataan
erillisiin dokumentteihin.

Tänne kirjataan myös lopuksi järjestelmän tunnetut ongelmat, joita ei ole korjattu.

## Asennustiedot

Järjestelmän asennus on syytä dokumentoida kahdesta näkökulmasta:

-   järjestelmän kehitysympäristö: miten järjestelmän kehitysympäristön saisi
    rakennettua johonkin toiseen koneeseen

-   järjestelmän asentaminen tuotantoympäristöön: miten järjestelmän saisi
    asennettua johonkin uuteen ympäristöön.

Asennusohjeesta tulisi ainakin käydä ilmi, miten käytettävä tietokanta ja
käyttäjät tulee ohjelmistoa asentaessa määritellä (käytettävä tietokanta,
käyttäjätunnus, salasana, tietokannan luonti yms.).

## Käynnistys- ja käyttöohje

Tyypillisesti tässä riittää kertoa ohjelman käynnistykseen tarvittava URL sekä
mahdolliset kirjautumiseen tarvittavat tunnukset. Jos järjestelmän
käynnistämiseen tai käyttöön liittyy joitain muita toimenpiteitä tai toimintajärjestykseen liittyviä asioita, nekin kerrotaan tässä yhteydessä.

Usko tai älä, tulet tarvitsemaan tätä itsekin, kun tauon jälkeen palaat
järjestelmän pariin !

