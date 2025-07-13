package com.pangapiserver.infrastructure.firebase.config;

//@Configuration
//public class FirebaseConfig {
//    @PostConstruct
//    public void init(){
//        try{
//            InputStream serviceAccount = new ClassPathResource("firebaseKey.json").getInputStream();
//            FirebaseOptions options = new FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//                    .build();
//
//            if (FirebaseApp.getApps().isEmpty()) {
//                FirebaseApp.initializeApp(options);
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}