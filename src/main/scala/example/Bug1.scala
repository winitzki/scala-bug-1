package example

object Bug1 extends App {
  // This works:
  for {
    x <- Left("error")
    y <- Right(1 + x)
  } yield x

  // This works too:
  implicit def safeInt(x: Int): Either[String, Int] = Right(x)

  val c: Either[String, Int] = Left("error") // Right(1)

  for {
    x <- c
    y <- 1 + x
  } yield x

  // But this causes a compiler error:
  for {
    x <- Left("error")
    y <- 1 + x
  } yield x

  // Here is the error:

  /*
Error:scalac: Error while emitting Bug1.scala
<error> (of class scala.reflect.internal.Types$ErrorType$)
Error:scalac: Error while emitting Bug1.scala
assertion failed: ClassBType.info not yet assigned: Lexample/Bug1$;
Warning:scalac: an unexpected type representation reached the compiler backend while compiling Bug1.scala: <error>. If possible, please file a bug on https://github.com/scala/bug/issues.
Warning:scalac: scala.MatchError: <error> (of class scala.reflect.internal.Types$ErrorType$)
Warning:scalac:
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.typeToBType(BTypesFromSymbols.scala:195)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.methodBTypeFromMethodType(BTypesFromSymbols.scala:129)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.methodBTypeFromSymbol(BTypesFromSymbols.scala:120)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.$anonfun$buildInlineInfoFromClassSymbol$6(BTypesFromSymbols.scala:577)
Warning:scalac: 	at scala.collection.Iterator$$anon$11.nextCur(Iterator.scala:473)
Warning:scalac: 	at scala.collection.Iterator$$anon$11.hasNext(Iterator.scala:479)
Warning:scalac: 	at scala.collection.Iterator.foreach(Iterator.scala:929)
Warning:scalac: 	at scala.collection.Iterator.foreach$(Iterator.scala:929)
Warning:scalac: 	at scala.collection.AbstractIterator.foreach(Iterator.scala:1417)
Warning:scalac: 	at scala.collection.TraversableOnce.toMap(TraversableOnce.scala:316)
Warning:scalac: 	at scala.collection.TraversableOnce.toMap$(TraversableOnce.scala:314)
Warning:scalac: 	at scala.collection.AbstractIterator.toMap(Iterator.scala:1417)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.buildInlineInfoFromClassSymbol(BTypesFromSymbols.scala:610)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.buildInlineInfo(BTypesFromSymbols.scala:514)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.setClassInfo(BTypesFromSymbols.scala:433)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypesFromSymbols.classBTypeFromSymbol(BTypesFromSymbols.scala:109)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeSkelBuilder$PlainSkelBuilder.genPlainClass(BCodeSkelBuilder.scala:91)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.CodeGen.genClass(CodeGen.scala:56)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.CodeGen.genClassDef$1(CodeGen.scala:26)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.CodeGen.genClassDefs$1(CodeGen.scala:44)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.CodeGen.$anonfun$genUnit$2(CodeGen.scala:43)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.CodeGen.genClassDefs$1(CodeGen.scala:43)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.CodeGen.genUnit(CodeGen.scala:47)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.GenBCode$BCodePhase.$anonfun$apply$1(GenBCode.scala:43)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.GenBCode$BCodePhase.apply(GenBCode.scala:43)
Warning:scalac: 	at scala.tools.nsc.Global$GlobalPhase.$anonfun$applyPhase$1(Global.scala:436)
Warning:scalac: 	at scala.tools.nsc.Global$GlobalPhase.applyPhase(Global.scala:429)
Warning:scalac: 	at scala.tools.nsc.Global$GlobalPhase.$anonfun$run$1(Global.scala:400)
Warning:scalac: 	at scala.tools.nsc.Global$GlobalPhase.$anonfun$run$1$adapted(Global.scala:400)
Warning:scalac: 	at scala.tools.nsc.Global$GlobalPhase.run(Global.scala:400)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.GenBCode$BCodePhase.super$run(GenBCode.scala:53)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.GenBCode$BCodePhase.$anonfun$run$1(GenBCode.scala:53)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.GenBCode$BCodePhase.run(GenBCode.scala:51)
Warning:scalac: 	at scala.tools.nsc.Global$Run.compileUnitsInternal(Global.scala:1452)
Warning:scalac: 	at scala.tools.nsc.Global$Run.compileUnits(Global.scala:1436)
Warning:scalac: 	at scala.tools.nsc.Global$Run.compileSources(Global.scala:1429)
Warning:scalac: 	at scala.tools.nsc.Global$Run.compile(Global.scala:1545)
Warning:scalac: 	at xsbt.CachedCompiler0.run(CompilerInterface.scala:131)
Warning:scalac: 	at xsbt.CachedCompiler0.run(CompilerInterface.scala:106)
Warning:scalac: 	at xsbt.CompilerInterface.run(CompilerInterface.scala:32)
Warning:scalac: 	at sun.reflect.GeneratedMethodAccessor24.invoke(Unknown Source)
Warning:scalac: 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
Warning:scalac: 	at java.lang.reflect.Method.invoke(Method.java:497)
Warning:scalac: 	at sbt.internal.inc.AnalyzingCompiler.call(AnalyzingCompiler.scala:237)
Warning:scalac: 	at sbt.internal.inc.AnalyzingCompiler.compile(AnalyzingCompiler.scala:111)
Warning:scalac: 	at sbt.internal.inc.AnalyzingCompiler.compile(AnalyzingCompiler.scala:90)
Warning:scalac: 	at org.jetbrains.jps.incremental.scala.local.IdeaIncrementalCompiler.compile(IdeaIncrementalCompiler.scala:40)
Warning:scalac: 	at org.jetbrains.jps.incremental.scala.local.LocalServer.compile(LocalServer.scala:30)
Warning:scalac: 	at org.jetbrains.jps.incremental.scala.remote.Main$.make(Main.scala:68)
Warning:scalac: 	at org.jetbrains.jps.incremental.scala.remote.Main$.nailMain(Main.scala:25)
Warning:scalac: 	at org.jetbrains.jps.incremental.scala.remote.Main.nailMain(Main.scala)
Warning:scalac: 	at sun.reflect.GeneratedMethodAccessor16.invoke(Unknown Source)
Warning:scalac: 	at com.martiansoftware.nailgun.NGSession.run(NGSession.java:319)
Warning:scalac: java.lang.AssertionError: assertion failed: ClassBType.info not yet assigned: Lexample/Bug1$;
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypes$ClassBType.info(BTypes.scala:622)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BTypes$ClassBType.isInterface(BTypes.scala:662)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeBodyBuilder$PlainBodyBuilder.genCallMethod(BCodeBodyBuilder.scala:1068)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeBodyBuilder$PlainBodyBuilder.genApply(BCodeBodyBuilder.scala:701)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeBodyBuilder$PlainBodyBuilder.genLoad(BCodeBodyBuilder.scala:296)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeBodyBuilder$PlainBodyBuilder.genStat(BCodeBodyBuilder.scala:80)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeBodyBuilder$PlainBodyBuilder.$anonfun$genBlock$1(BCodeBodyBuilder.scala:812)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeBodyBuilder$PlainBodyBuilder.genBlock(BCodeBodyBuilder.scala:812)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeBodyBuilder$PlainBodyBuilder.genLoad(BCodeBodyBuilder.scala:366)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeSkelBuilder$PlainSkelBuilder.emitNormalMethodBody$1(BCodeSkelBuilder.scala:601)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeSkelBuilder$PlainSkelBuilder.genDefDef(BCodeSkelBuilder.scala:633)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeSkelBuilder$PlainSkelBuilder.gen(BCodeSkelBuilder.scala:507)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeSkelBuilder$PlainSkelBuilder.$anonfun$gen$7(BCodeSkelBuilder.scala:509)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeSkelBuilder$PlainSkelBuilder.gen(BCodeSkelBuilder.scala:509)
Warning:scalac: 	at scala.tools.nsc.backend.jvm.BCodeSkelBuilder$PlainSkelBuilder.genPlainClass(BCodeSkelBuilder.scala:110)
*/
}
