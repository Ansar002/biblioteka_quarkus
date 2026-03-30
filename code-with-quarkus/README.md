# code-with-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_** Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- REST Client ([guide](https://quarkus.io/guides/rest-client)): Call REST services
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

### REST Client

Invoke different services through REST with JSON

[Related guide section...](https://quarkus.io/guides/rest-client)

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## Relacije u Bazi Podataka

### OneToOne relacije
- **Knjiga ↔ Izdavac**: Jedna knjiga ima jednog izdavača
- **Izdavac ↔ Knjiga**: Jedan izdavač može imati jednu knjigu

### OneToMany relacije (sa FetchType.LAZY)
- **Clan → Pozajmica**: Jedan član može imati više pozajmica
- **Knjiga → Pozajmica**: Jedna knjiga može imati više pozajmica

### ManyToMany relacije (sa FetchType.LAZY)
- **Knjiga ↔ Autor**: Jedna knjiga može imati više autora, a jedan autor može napisati više knjiga
- **Knjiga ↔ Kategorija**: Jedna knjiga može pripadati više kategorija, a jedna kategorija može imati više knjiga

---

## 📚 TESTIRANJE PREKO POSTMAN-a

### Korak 1: Import Postman Collection
1. Otvorite Postman
2. Kliknite **File → Import**
3. Izaberite fajl: `Biblioteka_API_Postman.json`
4. Collection će biti importovan sa svim endpointima

### Korak 2: Pokrenite Aplikaciju
```shell script
./mvnw quarkus:dev
```
- Aplikacija će biti dostupna na: `http://localhost:8080`

### Korak 3: Testiranje Endpointa

#### IZDAVAČI
- `POST /izdavaci/addIzdavac` - Dodaj izdavača
- `GET /izdavaci/getAllIzdavaci` - Svi izdavači
- `GET /izdavaci/getIzdavacByNaziv?naziv=Prosveta` - Pretraga po nazivu (@QueryParam)
- `GET /izdavaci/getIzdavacById/1` - Pretraga po ID-u (@PathParam)

#### AUTORI
- `POST /autori/addAutor` - Dodaj autora
- `GET /autori/getAllAutori` - Svi autori
- `GET /autori/getAutorByIme?ime=Ivo Andrić` - Pretraga po imenu (@QueryParam)
- `GET /autori/getAutorById/1` - Pretraga po ID-u (@PathParam)
- `GET /autori/getKnjigeByAutorId?id=1` - Sve knjige određenog autora

#### KATEGORIJE
- `POST /kategorije/addKategorija` - Dodaj kategoriju
- `GET /kategorije/getAllKategorije` - Sve kategorije
- `GET /kategorije/getKategorijaByNaziv?naziv=Istorija` - Pretraga po nazivu (@QueryParam)
- `GET /kategorije/getKategorijaById/1` - Pretraga po ID-u (@PathParam)
- `GET /kategorije/getKnjigeByKategorijaId?id=1` - Sve knjige određene kategorije

#### KNJIGE
- `POST /knjige/addKnjiga` - Dodaj knjagu sa autorima, kategorijama i izdavačem
  ```json
  {
    "naslov": "Na Drini ćuprija",
    "autori": [{"id": 1}],
    "kategorije": [{"id": 1}],
    "izdavac": {"id": 1}
  }
  ```
- `GET /knjige/getAllKnjige` - Sve knjige
- `GET /knjige/getKnjigaByNaslov?naslov=Na Drini ćuprija` - Pretraga po naslovu (@QueryParam)
- `GET /knjige/getKnjigaById/1` - Pretraga po ID-u (@PathParam)
- `GET /knjige/getKnjigeByAutorId/1` - Sve knjige određenog autora
- `GET /knjige/getKnjigeByKategorijaId/1` - Sve knjige određene kategorije
- `GET /knjige/getAutoriByKnjigaId?id=1` - Svi autori određene knjige
- `GET /knjige/getKategorijeByKnjigaId?id=1` - Sve kategorije određene knjige
- `GET /knjige/getPozajmiceByKnjigaId?id=1` - Sve pozajmice određene knjige

#### ČLANOVI
- `POST /clanovi/addClan` - Dodaj člana
  ```json
  {
    "ime": "Marko",
    "prezime": "Marković"
  }
  ```
- `GET /clanovi/getAllClanovi` - Svi članovi
- `GET /clanovi/getClanByName?ime=Marko&prezime=Marković` - Pretraga po imenu i prezimenu (@QueryParam)
- `GET /clanovi/getClanById/1` - Pretraga po ID-u (@PathParam)
- `GET /clanovi/getPozajmiceByClanId?id=1` - Sve pozajmice člana

#### POZAJMICE
- `POST /pozajmice/addPozajmica` - Dodaj pozajmicu
  ```json
  {
    "knjiga": {"id": 1},
    "clan": {"id": 1},
    "datumPozajmice": "2026-03-29"
  }
  ```
- `GET /pozajmice/getAllPozajmice` - Sve pozajmice
- `GET /pozajmice/getPozajmicaById/1` - Pretraga po ID-u (@PathParam)
- `GET /pozajmice/getPozajmiceByClanId?clanId=1` - Sve pozajmice člana (@QueryParam)
- `GET /pozajmice/getPozajmiceByKnjigaId?knjigaId=1` - Sve pozajmice knjige (@QueryParam)

---

## 🔄 SCHEDULER

Aplikacija ima `@Scheduler` koji:
1. **Svakih 1 minut** - Proverava sve aktivne pozajmice i loguje ih
2. **Svaki dan u ponoć** - Kreira dnevni izveštaj o pozajmicama

Logove možete videti u konzoli kada pokrenete aplikaciju.

---

## ✅ ZAHTEVI KOJI SU ISPUNJENI

✅ **1. Bar dve @OneToOne relacije** - Knjiga-Izdavac
✅ **2. Bar dve @OneToMany/@ManyToOne sa FetchType.LAZY** - Clan-Pozajmica, Knjiga-Pozajmica, Knjiga-Autor, Knjiga-Kategorija
✅ **3. Bar dve metode za pretragu sa @QueryParam/@PathParam** - Svaki resurs ima po nekoliko metoda
✅ **4. Testiranje dodavanja entiteta sa kolekcionama** - ResourceIntegrationTest.java
✅ **5. @GET endpointi za kolekcije** - getAutoriByKnjigaId, getKategorijeByKnjigaId, getPozajmiceByKnjigaId, itd.
✅ **6. Quarkus @Scheduler** - PozajmicaScheduler.java
✅ **7. Testiranje preko Postman-a** - Biblioteka_API_Postman.json

---

## 🧪 POKRETANJE TESTOVA

```shell script
./mvnw test
```

ili samo JUnit testove:

```shell script
./mvnw test -Dtest=ResourceIntegrationTest
```
