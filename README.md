[![Build Status](https://ligi.ci.cloudbees.com/job/AndroidHelper/badge/icon)](https://ligi.ci.cloudbees.com/job/AndroidHelper/)

The goal of this project is to provide code that is used in a lot of Android-Apps over and over again and this way:

 * shorten code in the apps ( and make them more readable this way )
 * deduplicate code and	    tests

This library is released under MIT license. Feel free to use it. Pull requests are welcome.

You can either [download the jar](https://ligi.ci.cloudbees.com/job/AndroidHelp\er/lastSuccessfulBuild/artifact/build/libs/) and use it - or pull the lib via maven - this is how this is done in gradle:

```groovy
repositories {
    maven {
        url "https://raw.github.com/ligi/ligis-mvn-repo/master"
    }
}

dependencies {
    compile 'org.ligi:AndroidHelper:0.20'
}

```

most helpers are invoked that way

```java
 // context
 EditText editText = getEditText();
 
 // invoke AndroidHelper
 AndroidHelper.at(editText).changeTextIfNeeded("new text?");
```
