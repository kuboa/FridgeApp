FridgeApp
FridgeApp は、冷蔵庫にある食材を元にレシピを検索できるアプリケーションです。冷蔵庫の中身を登録し、それを使って作れる料理のレシピを見つけることができます。


前提条件
このアプリケーションを実行するためには、以下のソフトウェアがシステムにインストールされている必要があります:

Java 17 以上
JavaFX SDK 17 以上

アプリインストール方法
プロジェクトのセットアップとビルドを行うには、以下の手順に従ってください。

コマンドプロンプトを開き、Java ディレクトリまで移動します。
cd path/to/demo
Maven を使用してプロジェクトをクリーンし、依存関係をインストールします。
mvn clean install
実行可能な JAR ファイルをパッケージングします。
mvn clean package


実行方法
以下のコマンドを使用してアプリケーションを実行します。JavaFX ライブラリへのパスは環境に合わせ手変更してください。
java --module-path \YourPath\javafx-sdk-22.0.1\lib --add-modules javafx.controls,javafx.fxml -jar target/FridgeApp.jar


注意点
アプリケーションのインターフェースは英語のみ対応しています。
食材名等の入力は英語で行う必要があります。
