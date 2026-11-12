<div align="center">

  <h1>SHADOW</h1>

  <p>
    A personal, community-maintained white-label fork of Nuvio Mobile.
    <br />
    Shadow preserves the upstream app's media-library experience and integrations while applying its own app identity.
  </p>

  <p><strong>Fork attribution:</strong> Shadow is based on Nuvio Mobile. The upstream project, its contributors, third-party services, trademarks, and license notices remain acknowledged where applicable.</p>

</div>

## Project

This repository contains the Shadow-branded Android and iOS application. The app is built with Kotlin Multiplatform and Compose Multiplatform.

## Build from source

### Android

Android development requires Android Studio and the Android SDK.

```bash
./gradlew :androidApp:assembleFullDebug
```

### iOS

iOS development requires macOS and Xcode.

```bash
env NUVIO_IOS_DISTRIBUTION=full xcodebuild \
  -project iosApp/iosApp.xcodeproj \
  -scheme iosApp \
  -configuration Debug \
  -sdk iphonesimulator \
  -derivedDataPath build/ios-derived-full-simulator \
  CODE_SIGNING_ALLOWED=NO \
  build
```

The shared app is built with Kotlin Multiplatform and Compose Multiplatform.

## Upstream and license

Shadow is a fork of [Nuvio Mobile](https://github.com/NuvioMedia/NuvioMobile). Upstream copyright notices and third-party attributions are retained in the source.

[GNU General Public License v3.0](./LICENSE)
