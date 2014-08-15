[![Build Status](https://travis-ci.org/ligi/AXT.svg?branch=master)](https://travis-ci.org/ligi/AXT)

Why?
====

The aim of this project is to provide code that is used in a lot of Android-Apps over and over again this way:

 * reduce code in the apps ( -> more readable )
 * deduplicate code and	tests ( -> save space & time )

How?
===

You can either [ ![Download](https://api.bintray.com/packages/ligi/maven/axt/images/download.png) ](https://bintray.com/ligi/maven/axt/_latestVersion) and use it - or pull the lib via jcenter ( is like mavenCentral, but better ) - this is how you should do it via gradle:

```groovy
repositories {
    jcenter()
}

dependencies {
    compile 'org.ligi:AXT:0.28'
}

```

Android now uses jcenter by default, so you might not need the first part.


most helpers are invoked that way

```java
 // context
 EditText editText = getEditText();
 
 // invoke AXT
 AXT.at(editText).changeTextIfNeeded("new text?");
```
there are also things beside these helpers that can reduce your code - e.g. a SimpleTextWatcher to reduce this common mess:

```java
textView.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // nothing
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // nothing
    }

    @Override
    public void afterTextChanged(Editable s) {
        theRealWork(s);
    }
});
```
to this:
```java
textView.addTextChangedListener(new SimpleTextWatcher() {
    @Override
    public void afterTextChanged(Editable s) {
        theRealWork(s);
    }
});
```

Why the Name?
=============

the acronymes comes from <b>A</b>ndroid e<b>XT</b>ensions and also leaned torwards dagger and butterknife that aim on reducing code in android apps - AXT is german for AXE - so also a cutting tool.


Terms
=====

This library is released under Apache 2 license. Feel free to use it. Pull requests are welcome.


    Copyright 2013 ligi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
