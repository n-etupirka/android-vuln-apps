# android-vuln-apps
Androidアプリの脆弱性を作り込んだサンプルアプリおよびPoC集

## 0x01 Inadequate access restrictions
### 概要
ディープリンクを使用して指定された任意のURLにアクセスしてしまう脆弱性。外部から受け取ったURLにWebViewでアクセスする機能があり、適切にアクセス制御が行われていない場合に作り込まれる。

さらなる脆弱性を悪用するための入り口やフィッシングなどに悪用される恐れがある。

### 脆弱性実例
- https://jvn.jp/jp/JVN25850723/

### 参考
- https://www.lac.co.jp/lacwatch/people/20210625_002645.html

## 0x02 Access to private components
### 概要
外部から非公開のコンポーネントへアクセス可能となる脆弱性。WebViewによるIntentスキームのサポートを**shouldOverrideUrlLoading**メソッドによって独自実装しており、その実装に不備がある場合に作り込まれる。

この脆弱性を悪用することによって本来アクセスできないコンポーネントへアクセス可能になる。また、アクセス先のコンポーネントの機能によってはさらなる攻撃が可能となる恐れがある。

### 脆弱性実例
- https://hackerone.com/reports/1065500

### 参考
- https://www.m3tech.blog/entry/android-webview-intent-scheme
- https://medium.com/@dPhoeniixx/tiktok-for-android-1-click-rce-240266e78105
- https://www.mbsd.jp/Whitepaper/IntentScheme.pdf

## 0x03 Steal tokens
### 概要

// TODO
