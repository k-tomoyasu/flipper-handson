# Flipper-Sample
モバイルアプリのデバッグツールFlipperのサンプルリポジトリです。
Flipperを導入するとネットワーク通信やレイアウト構造、ストレージ覗く・書き換えるなどが手軽に出きて開発効率が上がります。
[Flipper公式](https://fbflipper.com/)

## ハンズオン
Flipper導入のハンズオン実施のためにmasterブランチはFlipper導入前の状態になっています。
`installed` ブランチに導入済みのサンプルアプリを置いています。
サンプルアプリは[公式サンプル](https://github.com/facebook/flipper)に少し手を入れたものとなっています。

## サンプルの対応プラットフォーム
- iOS
- Android

## サンプルで導入しているプラグイン
Flipperはプラグインを追加することで、デバッグの幅を広げられます。
プラグインには端末内データベースを読み書きするものや、メモリリーク検知(LeakCanary)などがあります。  
サンプルアプリでは以下のプラグインを入れています。
- Layout Inspector
- Network
- Images
- Shared Preferences
