// 예시: app/build.gradle.kts에서 사용하는 방법

plugins {
    id("android-app-dependency")  // Android 앱에 필요한 모든 의존성을 자동으로 추가
    id("dependency-validation")    // 의존성 검증 태스크 추가
}

// 또는 개별적으로 사용
plugins {
    id("compose-dependency")       // Compose 관련 의존성만 추가
    id("networking-dependency")    // 네트워킹 관련 의존성만 추가
    id("testing-dependency")       // 테스팅 관련 의존성만 추가
}

// 예시: data/remote/build.gradle.kts에서 사용하는 방법
plugins {
    id("data-layer-dependency")    // 데이터 레이어에 필요한 의존성 자동 추가
}

// 예시: domain/usecase/build.gradle.kts에서 사용하는 방법
plugins {
    id("domain-layer-dependency")  // 도메인 레이어에 필요한 의존성 자동 추가
}

// 예시: presentation/component/build.gradle.kts에서 사용하는 방법
plugins {
    id("presentation-layer-dependency")  // 프레젠테이션 레이어에 필요한 의존성 자동 추가
}

// 의존성 검증 태스크 실행
// ./gradlew validateDependencies

// 의존성 업데이트 체크 태스크 실행
// ./gradlew checkDependencyUpdates







