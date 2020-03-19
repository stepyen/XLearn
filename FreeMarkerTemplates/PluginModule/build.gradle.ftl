apply plugin: 'com.android.library'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions'

android {
    compileSdkVersion setup.compileSdk

    defaultConfig {
        minSdkVersion setup.minSdk
        targetSdkVersion setup.targetSdk
        versionCode release.versionCode
        versionName release.versionName

    }

    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android.txt'), '../../app/proguard-rules.pro'
        }
    }
    repositories {
        flatDir {
            dirs 'libs',
                    '../${plugin_name}/libs'
        }
    }

}

dependencies {
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation project(':modules:BaseService')


   
}