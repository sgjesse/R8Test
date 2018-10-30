#! /bin/bash
./gradlew clean assembleRelease
$ANDROID_BUILD_TOOLS/zipalign -f -v 4 app/build/outputs/apk/release/app-release-unsigned.apk app-release-unsigned-aligned.apk
$ANDROID_BUILD_TOOLS/apksigner sign -v --ks release.keystore --min-sdk-version 19 --out app-release.apk app-release-unsigned-aligned.apk

