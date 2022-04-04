# scalajs-fake-insecure-java-securerandom

`scalajs-fake-insecure-java-securerandom` provides compatibility stubs for `java.security.SecureRandom` in Scala.js.

## ⚠️ This library is insecure by design! ⚠️

This library is insecure by design.
It does not provide a cryptographically secure implementation of `SecureRandom`.
It is only there to preserve support for `java.util.UUID.randomUUID()` from Scala.js core previous to v1.10.0 in environments where secure UUID generation is not a requirement, and if there is no other choice.

Use [`scalajs-java-securerandom`](https://github.com/scala-js/scala-js-java-securerandom) instead.

## Usage from a library

**Never, ever depend on `scalajs-fake-insecure-securerandom` from library code!**

If you do, you will expose your users to insecure code.

Always use [`scalajs-java-securerandom`](https://github.com/scala-js/scala-js-java-securerandom) instead.

## Usage from an application

**Almost never** depend on `scalajs-fake-insecure-securerandom`.
Use [`scalajs-java-securerandom`](https://github.com/scala-js/scala-js-java-securerandom) instead if possible, notably if you only target browsers and/or Node.js.

If you have no other choice, use the following dependency:

```scala
libraryDependencies += ("org.scala-js" %%% "scalajs-fake-insecure-java-securerandom" % "1.0.0-SNAPSHOT").cross(CrossVersion.for3Use2_13)
```

When using a `crossProject`, add the above in `.jsSettings(...)`.

You can then use `java.security.SecureRandom` from your code, and by extension, the `java.util.UUID.randomUUID()` method.
Keep in mind that the generated random numbers (and random UUIDs) will *not* be cryptographically secure.

## License

`scalajs-fake-insecure-securerandom` is distributed under the [Apache 2.0 license](./LICENSE.txt), like Scala.js itself.
