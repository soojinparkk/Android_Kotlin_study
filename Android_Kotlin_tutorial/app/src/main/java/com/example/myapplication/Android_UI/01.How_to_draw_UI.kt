package com.example.myapplication.Android_UI

// 안드로이드에서 화면을 그리는 방법
// -> XML 이용
// XML: DSL Language (= Domain Specific Language)
// 안드로이드 UI를 그리기 위해 특화된 언어

// 핸드폰마다 화면 크기가 다 다름 -> 어떻게 그려야 할까?
// 픽셀, dpi, dp 단위
// 픽셀: 핸드폰 화면에서 빛이 나오는 전구 -> 가장 작은 단위
// dpi: dot per inch
//      ex) ldpi -> 120 (1인치에 120픽셀)
//          mdpi -> 160
//          hdpi -> 240
//          xhdpi -> 370
//          xxhdpi -> 480
//          xxhdpi -> 640
//       핸드폰마다 화면의 크기가 다르기 때문에 화면이 다르게 보임
// dp 단위: Density Independent Pixcel
//         픽셀 독립적인 단위
//         -> 해상도가 다르다고 해서 다르지 않고, 어느 핸드폰에서나 똑같이 보임

// -> 픽셀이 아닌 dp 단위를 사용
