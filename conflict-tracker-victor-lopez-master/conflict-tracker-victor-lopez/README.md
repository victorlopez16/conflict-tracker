Conflict Tracker és una API REST molt bàsica per gestionar informació sobre conflictes internacionals (conflictes, països, faccions i esdeveniments). El frontend és testimonial (index.html) i l'API s'accedeix sota el prefix `/api/v1`.

Instruccions d'ús: Instal·lació
1. Clona el repositori:
   git clone <url-del-repositori>
2. Obre el projecte amb el teu IDE
3. Executa l'aplicació principal
4. L'aplicació estarà disponible a:
   http://localhost:8080/web/conflicts

Exemples d'ús:

- Obtenir tots els conflictes:
  GET http://localhost:8080/conflicts

- Obtenir un conflicte per ID:
  GET http://localhost:8080/conflicts/{id}

- Crear un nou conflicte:
  POST http://localhost:8080/api/v1/conflicts
  Body (JSON):
  {
    "name": "Guerra de Ucraïna",
    "startConflict": "2022-02-24",
    "status": "ACTIVE",
    "description": "Conflicte armat iniciat després de la invasió russa d'Ucraïna.",
    "countryId": [1, 2]
  }