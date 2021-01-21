import firebase from 'firebase/app'
import 'firebase/storage'

  // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: "AIzaSyAiSuXRY7E38pKAmVLrvGq2xUC1afu4ILE",
    authDomain: "airbnbclone-a3ae5.firebaseapp.com",
    databaseURL: "https://airbnbclone-a3ae5.firebaseio.com",
    projectId: "airbnbclone-a3ae5",
    storageBucket: "airbnbclone-a3ae5.appspot.com",
    messagingSenderId: "384708305140",
    appId: "1:384708305140:web:00a66ca9ede17931ec3ea1"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  const db = firebase.storage().ref()

  export { db };
