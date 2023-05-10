
## shadow-browser-chui

Like shadow-browser, but replace Shadow's own cljs test runner namespace with
[Chui](https://github.com/lambdaisland/chui) (`:runner-ns lambdaisland.chui.shadow.browser-runner`)

### How to run:

```
shadow-cljs server
```

separate terminal

```
shadow-cljs compile test
# or
shadow-cljs watch test
```

Open http://localhost:8000 in the browser. If you used `watch` then changing the
tests and saving will actually re-run them in the browser.



