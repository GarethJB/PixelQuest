# Shared Presentation Resources

이 모듈은 PixelQuest 앱의 공유 리소스를 관리합니다.

## 구조

### Android 리소스 (현재 사용 중)
- `src/main/res/values/` - 문자열, 크기 등 Android 리소스

### Compose Multiplatform Resources (향후 KMP 확장 대비)
- `src/commonMain/composeResources/drawable/` - 이미지 리소스 (구조만 준비됨)
- 현재는 Android 전용 프로젝트이므로 Android 리소스 시스템 사용
- 향후 iOS 등 다른 플랫폼 지원 시 Compose Multiplatform Resources 활성화 예정

## 이미지 리소스 사용 방법

### 현재 (Android 전용)
Android 리소스 시스템을 사용합니다:
- `src/main/res/drawable/` - 이미지 리소스
- `R.drawable.icon_name` 형태로 접근

### 향후 (KMP 확장 시)
Compose Multiplatform Resources를 사용할 수 있도록 구조가 준비되어 있습니다:
- `src/commonMain/composeResources/drawable/` - 이미지 리소스
- 플러그인 활성화 후 `Res.drawable.icon_name` 형태로 접근 가능

## 리소스 네이밍 규칙

- **이미지**: `snake_case` 사용
  - 예: `icon_home.png`, `logo_app.webp`, `background_welcome.jpg`
- **의미 있는 이름 사용**
  - 좋은 예: `icon_home.png`, `button_submit.png`
  - 나쁜 예: `img1.png`, `temp.png`

## 모듈 의존성

다른 모듈에서 이 리소스를 사용하려면:

```kotlin
dependencies {
    implementation(project(":shared:presentation:resources"))
}
```

## 참고

- [Compose Multiplatform Resources 공식 문서](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-resources.html)
- 빌드 후 `Res` 객체가 자동 생성됩니다.
- 타입 안전한 리소스 접근이 가능합니다.
