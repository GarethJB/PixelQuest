# Build Logic 모듈 구조

이 디렉토리는 Gradle Convention Plugins를 관리하는 build-logic 모듈입니다.

## 모듈 구조

### 1. `convention` 모듈
**책임**: 프로젝트 타입별 기본 빌드 설정 및 플러그인 자동 적용

#### 제공하는 플러그인:
- `android-application-convention`: Android 앱 모듈 설정
- `android-library-convention`: Android 라이브러리 모듈 설정
- `kotlin-library-convention`: Kotlin JVM 라이브러리 모듈 설정
- `compose-convention`: Compose 빌드 설정
- `kotlin-options-convention`: Kotlin 컴파일 옵션 설정

#### 주요 클래스:
- `AndroidApplicationConventionPlugin`: Android 앱 모듈에 필요한 플러그인과 의존성 자동 적용
- `AndroidLibraryConventionPlugin`: Android 라이브러리 모듈에 필요한 플러그인과 의존성 자동 적용
- `KotlinLibraryConventionPlugin`: Kotlin JVM 라이브러리 모듈 설정
- `ComposeConventionPlugin`: Compose 빌드 설정 및 의존성 추가
- `KotlinOptionsConventionPlugin`: Kotlin 컴파일 옵션 표준화
- `VersionCatalogExtensions`: Version Catalog 접근을 위한 확장 함수

---

### 2. `dependency` 모듈
**책임**: 의존성 관리, 그룹화, 검증

#### 제공하는 플러그인:

##### 기능별 의존성 그룹:
- `compose-dependency`: Compose 관련 의존성
- `networking-dependency`: Retrofit, OkHttp 등 네트워킹 의존성
- `database-dependency`: Room 관련 의존성
- `testing-dependency`: 테스트 라이브러리 의존성

##### 레이어별 의존성:
- `android-app-dependency`: Android 앱 모듈 전체 의존성
- `data-layer-dependency`: 데이터 레이어 의존성
- `domain-layer-dependency`: 도메인 레이어 의존성
- `presentation-layer-dependency`: 프레젠테이션 레이어 의존성

##### 의존성 관리:
- `dependency-validation`: 의존성 버전 검증 및 호환성 체크
- `dependency-update-check`: 의존성 업데이트 체크

#### 주요 클래스:
- `DependencyBundlesPlugin`: 기능별 의존성 그룹화 (Compose, Networking, Database, Testing)
- `LayerDependencyPlugin`: 아키텍처 레이어별 의존성 자동화
- `DependencyValidationPlugin`: 의존성 버전 검증
- `VersionCatalogExtensions`: Version Catalog 접근을 위한 확장 함수

---

### 3. `testing` 모듈
**책임**: 테스트 설정 및 구성 관리

#### 제공하는 플러그인:
- `test-configurations-convention`: 테스트 설정 표준화
- `unit-test-convention`: Unit Test 의존성 및 설정
- `android-test-convention`: Android Instrumentation Test 의존성 및 설정
- `test-fixtures-convention`: Test Fixtures 기능 활성화

#### 주요 클래스:
- `TestConfigurationsConventionPlugin`: 테스트 태스크 기본 설정 및 로깅 설정
- `UnitTestConventionPlugin`: Unit Test에 필요한 의존성 및 설정 자동 추가
- `AndroidTestConventionPlugin`: Android Instrumentation Test에 필요한 의존성 및 설정 자동 추가
- `TestFixturesConventionPlugin`: Test Fixtures 기능 활성화
- `VersionCatalogExtensions`: Version Catalog 접근을 위한 확장 함수

---

## 사용 예시

### Android 앱 모듈
```kotlin
// app/build.gradle.kts
plugins {
    id("android-application-convention")  // convention: 기본 설정
    id("android-app-dependency")          // dependency: 모든 의존성
    id("dependency-validation")           // dependency: 검증
}
```

### 데이터 레이어 모듈
```kotlin
// data/remote/build.gradle.kts
plugins {
    id("android-library-convention")      // convention: 라이브러리 설정
    id("data-layer-dependency")           // dependency: 레이어별 의존성
    id("networking-dependency")           // dependency: 기능별 의존성
    id("unit-test-convention")            // testing: Unit Test 설정
}
```

### 도메인 레이어 모듈
```kotlin
// domain/usecase/build.gradle.kts
plugins {
    id("kotlin-library-convention")       // convention: Kotlin 라이브러리 설정
    id("domain-layer-dependency")         // dependency: 도메인 레이어 의존성
    id("unit-test-convention")            // testing: Unit Test 설정
}
```

### 프레젠테이션 레이어 모듈
```kotlin
// presentation/component/build.gradle.kts
plugins {
    id("android-library-convention")      // convention: 라이브러리 설정
    id("compose-convention")              // convention: Compose 설정
    id("presentation-layer-dependency")   // dependency: 프레젠테이션 레이어 의존성
    id("android-test-convention")         // testing: Android Test 설정
}
```

---

## 모듈 간 관계

```
convention (프로젝트 타입 설정)
    ↓
dependency (의존성 추가)
    ↓
testing (테스트 설정)
    ↓
실제 프로젝트 모듈들
```

각 모듈은 독립적으로 사용할 수 있으며, 필요에 따라 조합하여 사용할 수 있습니다.

---

## 확장 방법

### 새로운 Convention Plugin 추가
1. `convention/src/main/kotlin/` 디렉토리에 새 플러그인 클래스 작성
2. `convention/build.gradle.kts`의 `gradlePlugin` 블록에 등록

### 새로운 Dependency Plugin 추가
1. `dependency/src/main/kotlin/` 디렉토리에 새 플러그인 클래스 작성
2. `dependency/build.gradle.kts`의 `gradlePlugin` 블록에 등록

### 새로운 Testing Plugin 추가
1. `testing/src/main/kotlin/` 디렉토리에 새 플러그인 클래스 작성
2. `testing/build.gradle.kts`의 `gradlePlugin` 블록에 등록




