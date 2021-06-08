# General Utils library
This library has all libraries which are generally added in **Android Project**

### Adding to project
In allprojects -> repositories block of root **build.gradle** file

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
        // Add here
    }
}
```

add below code
```groovy
maven { url 'https://jitpack.io' }
```

Add classpath in dependencies block of build script in root level **build.gradle**

```groovy
    classpath "androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5"
```

Replace plugins block of your project level **build.gradle** file with below code

```groovy
plugins {
    id 'com.android.application'
    id 'kotlin-android'
    id 'kotlin-kapt'
    id 'kotlin-parcelize'
    id 'androidx.navigation.safeargs.kotlin'
}
```

Add following code in dependencies block of your project level **build.gradle** file. Add as it is, do not change variable names or versions of kapt.

```groovy
   implementation 'com.github.ankitahuja0508:GeneralUtils:1.1.9'
    
    // Epoxy kapy
    kapt "com.airbnb.android:epoxy-processor:$epoxy_version"

    // Glide kapt
    kapt "com.github.bumptech.glide:compiler:$glide_version"

    // Moshi kapt
    kapt "com.squareup.moshi:moshi-kotlin-codegen:$moshi_version"
```