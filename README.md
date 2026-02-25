
# BatteryLogger15min

This project logs the battery percentage to a CSV file every 15 minutes using WorkManager.

## Build locally (debug APK)
- Open in Android Studio
- Build > Build APK(s)
- APK: `app/build/outputs/apk/debug/app-debug.apk`

## GitHub Actions (auto build debug APK)
1. Push this project to a GitHub repository
2. Ensure GitHub Actions is enabled for the repo
3. The workflow in `.github/workflows/android.yml` will build on push or manual dispatch
4. Download the `app-debug.apk` from the Actions run artifacts
