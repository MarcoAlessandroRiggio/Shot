# Shot

*" Scusi*
  *che ne pensa di un romantico alla Scala?*<br>
  *quando canta le canzoni della mala scola*<br>
  *quasi centomila Montenegro e Bloody Mary*<br>
  *mocassini gialli e sentimenti chiaro-scuri "*

*[Francesco Bianconi](https://it.wikipedia.org/wiki/Francesco_Bianconi), [Un romantico a Milano](https://www.youtube.com/watch?v=yV5NQyT3DFE).*

This application is written with Android Studio and use Firebase and Google Service.
The goal of the application is count the number of [Montenegro's](https://en.wikipedia.org/wiki/Amaro_Montenegro) shot.
An example of firebase project for the server is [Shot-BackEnd](https://github.com/MarcoAlessandroRiggio/Shot-BackEnd).
This application is write for play with friends and it have some problem:
1. You must add a default record in firebase realtime cloud for each user like this:
>  {
>      "Marco": {
>        "value": 0
>      }
>  }
  
2. If there are two players with the same name you must change the code for use something different from "account.getGivenName()" (perhaps concatenating it with "account.getFamilyName()")

For use this application you need to:
1. create a firebase project and [register your application](https://firebase.google.com/docs/android/setup)
2. add your google-services.json in the root of the project
3. Enjoy.

For build the .apk it's enought run grandle assembleDebug.
