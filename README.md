# PopcornTime

## Wymagania

### Backend (Java + Spring Boot)

1. **Java**: Zainstaluj **JDK 21** lub nowszą wersję. Można to zrobić z oficjalnej strony Oracle lub OpenJDK.
2. **Maven**: Używany do budowy projektu. Można go pobrać z [oficjalnej strony Apache Maven](https://maven.apache.org/download.cgi).
3. **Docker**: Służy do uruchamiania kontenerów w aplikacji. Docker jest wymagany do uruchomienia niektórych zależności.

### Frontend (React)

1. **Node.js**: Zainstaluj **Node.js** (zalecana wersja LTS) z [oficjalnej strony](https://nodejs.org/en/download/).
2. **npm**: Menedżer pakietów Node.js (powinien być zainstalowany automatycznie razem z Node.js).

## Uruchamianie backendu (VS Code)

Aby uruchomić backend aplikacji lokalnie, wykonaj poniższe kroki:

1. **Uruchom kontener Docker**

   Upewnij się, że masz zainstalowanego Dockera i jest uruchomiony. Następnie w terminalu wejdź do katalogu `backend` i uruchom kontener:

   ```bash
   docker-compose up -d
   ```

2. **Zbuduj projekt Mavenem**

   W katalogu `backend` uruchom polecenie, aby zbudować projekt:

   ```bash
   cd backend
   mvn clean install
   ```

3. **Uruchom aplikację Spring Boot**

   Następnie uruchom aplikację za pomocą Mavena:

   ```bash
   mvn spring-boot:run
   ```

Aplikacja backendowa będzie dostępna pod adresem: [http://localhost:8080](http://localhost:8080).

## Dokumentacja API (Swagger)

Aby zapoznać się z pełną listą dostępnych endpointów oraz przetestować je bezpośrednio z poziomu przeglądarki, skorzystaj z interfejsu Swagger UI:

🔗 **[http://127.0.0.1:8080/swagger-ui/index.html](http://127.0.0.1:8080/swagger-ui/index.html)**

Swagger zostaje automatycznie uruchomiony razem z backendem, pod warunkiem, że aplikacja działa lokalnie na porcie `8080`.

---

## Uruchamianie frontend (React)

Aby uruchomić frontend aplikacji lokalnie, wykonaj poniższe kroki:

1. **Zainstaluj zależności npm**

   Przejdź do katalogu `frontend` i zainstaluj wszystkie zależności:

   ```bash
   cd frontend
   npm install
   ```

2. **Uruchom aplikację React**

   Następnie uruchom aplikację za pomocą polecenia:

   ```bash
   npm start
   ```

Aplikacja frontendowa będzie dostępna pod adresem: [http://localhost:3000](http://localhost:3000).

---

## Problemy i rozwiązania

* **Jeśli nie możesz uruchomić kontenera Docker**: Upewnij się, że Docker jest poprawnie zainstalowany i uruchomiony na Twoim systemie.
* **Problemy z zależnościami npm**: Upewnij się, że masz odpowiednią wersję Node.js. Jeśli występują problemy z wersjami pakietów, spróbuj usunąć folder `node_modules` i ponownie uruchomić `npm install`.
