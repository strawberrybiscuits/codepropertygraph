package io.shiftleft.queryprimitives.steps.types.structure

import gremlin.scala._
import io.shiftleft.codepropertygraph.generated.{EdgeTypes, NodeTypes}
import io.shiftleft.codepropertygraph.generated.nodes
import io.shiftleft.queryprimitives.steps.NodeSteps
import io.shiftleft.queryprimitives.steps.Implicits.GremlinScalaDeco
import io.shiftleft.queryprimitives.steps.types.propertyaccessors.NameAccessors
import shapeless.HList

/**
  * A namespace, e.g., Java package or C# namespace
  * */
class Namespace[Labels <: HList](raw: GremlinScala.Aux[nodes.Namespace, Labels])
    extends NodeSteps[nodes.Namespace, Labels](raw)
    with NameAccessors[nodes.Namespace, Labels] {

  /**
    * The source files associated with the namespace
    * */
  override def file: File[Labels] =
    new File[Labels](raw.in(EdgeTypes.REF).in(EdgeTypes.AST).cast[nodes.File])

  /**
    * The type declarations defined in this namespace
    * */
  def typeDecl: TypeDecl[Labels] =
    new TypeDecl[Labels](
      raw
        .in(EdgeTypes.REF)
        .out(EdgeTypes.AST)
        .hasLabel(NodeTypes.TYPE_DECL)
        .cast[nodes.TypeDecl])

  /**
    * Methods defined in this namespace
    * */
  def method: Method[Labels] =
    typeDecl.method
}
