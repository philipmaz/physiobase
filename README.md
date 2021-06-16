# physiobase

Phisiobase to aplikacja webowa przeznaczona dla fizjoterapeutów i trenerów personalnych 
umożliwiająca zapisywanie postępów terapii i treningów w codziennej pracy z pacjentami.

Aplikacja napisana jest w języku Java z wykorzystaniem frameworków Spring oraz Hibernate.

Fukncjonalności:
- logowanie jako administrator/fizjoterapeuta 
(spersonalizowany dostęp do aplikacji, tylko właściciel aplikacji może dodawac użytkowników/adminów, hasło zabezpieczone z wykorzystaniem biblioteki jbcrypt, )
- dodawanie/usuwanie/modyfikacja danych Pacjenta
- dodawanie/usuwanie/modyfikacja danych poszczególnej wizyty pacjenta
- zapisywanie danych do bazy danych mySQL
- walidacja danych przekazywanych w formularzu
- wyświetlanie karty pacjenta
- dodawanie plików: zdjęc/skanów w formacie .jpg

