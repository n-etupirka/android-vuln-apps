# vuln-apps
Sample collection of vulnerable Android applications

## Inadequate access restrictions
### Overview
ディープリンクを使用して指定された任意のURLにアクセスしてしまう脆弱性。外部から受け取ったURLにWebViewでアクセスする機能があり、適切にアクセス制御が行われていない場合に作り込まれる。

さらなる脆弱性を悪用するための入り口やフィッシングなどに悪用される恐れがある。

### Example
- https://jvn.jp/jp/JVN25850723/

### Reference
- https://www.lac.co.jp/lacwatch/people/20210625_002645.html

## Access to private components
### Overview
外部から非公開のコンポーネントへアクセス可能となる脆弱性。WebViewによるIntentスキームのサポートを**shouldOverrideUrlLoading**メソッドによって独自実装しており、その実装に不備がある場合に作り込まれる。

この脆弱性を悪用することによって本来アクセスできないコンポーネントへアクセス可能になる。また、アクセス先のコンポーネントの機能によってはさらなる攻撃が可能となる恐れがある。

### Example
- https://hackerone.com/reports/1065500

### Reference
- https://www.m3tech.blog/entry/android-webview-intent-scheme
- https://medium.com/@dPhoeniixx/tiktok-for-android-1-click-rce-240266e78105
- https://www.mbsd.jp/Whitepaper/IntentScheme.pdf
