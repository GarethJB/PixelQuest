# Compose Resources

이 디렉토리는 Compose Multiplatform Resources를 위한 리소스 파일을 포함합니다.

## 디렉토리 구조

```
composeResources/
  drawable/              # 이미지 리소스
    icon_home.png
    logo_app.webp
  drawable-dark/         # 다크 테마용 이미지 (선택)
    icon_home.png
  drawable-hdpi/         # 고밀도 화면용 이미지 (선택)
    icon_home.png
```

## 지원 형식

- PNG (.png)
- JPEG (.jpg, .jpeg)
- WebP (.webp) - 권장

## 사용 예시

```kotlin
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import org.jetbrains.compose.resources.painterResource
import com.jb.pixelquest.shared.presentation.resources.Res

@Composable
fun ExampleImage() {
    Image(
        painter = painterResource(Res.drawable.icon_home),
        contentDescription = "Home"
    )
}
```
