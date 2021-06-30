package overflowdb.traversal.testdomains.gratefuldead

import overflowdb.traversal._
import overflowdb.{NodeRef, NodeDb}

class SongDb(ref: NodeRef[SongDb]) extends NodeDb(ref) {
  private var _name: String = null
  private var _songType: String = null
  private var _performances: Integer = null

  def name: String = _name
  def songType: String = _songType
  def performances: Integer = _performances

  /* Song --- followedBy --- Song */
  def followedBy: Traversal[Song] = out(FollowedBy.Label).toScalaAs[Song]

  /* Artist <-- sungBy --- Song */
  def sungBy: Traversal[Artist] = out(SungBy.Label).toScalaAs[Artist]

  /* Artist <-- writtenBy --- Song */
  def writtenBy: Traversal[Artist] = out(WrittenBy.Label).toScalaAs[Artist]

  override def valueMap = {
    val properties = new java.util.HashMap[String, Any]
    if (_name != null) properties.put(Song.PropertyNames.Name, _name)
    if (_songType != null) properties.put(Song.PropertyNames.SongType, _songType)
    if (_performances != null) properties.put(Song.PropertyNames.Performances, _performances)
    properties
  }

  override def property(key: String) =
    key match {
      case Song.PropertyNames.Name => _name
      case Song.PropertyNames.SongType => _songType
      case Song.PropertyNames.Performances => _performances
      case _ => null
    }

  override protected def updateSpecificProperty(key: String, value: Object) =
    key match {
      case Song.PropertyNames.Name =>
        _name = value.asInstanceOf[String]
      case Song.PropertyNames.SongType =>
        _songType = value.asInstanceOf[String]
      case Song.PropertyNames.Performances =>
        _performances = value.asInstanceOf[Integer]
      case _ =>
        throw new RuntimeException("property with key=" + key + " not (yet) supported by " + this.getClass().getName());
    }

  override protected def removeSpecificProperty(key: String) =
    key match {
      case Song.PropertyNames.Name => _name = null
      case Song.PropertyNames.SongType => _songType = null
      case Song.PropertyNames.Performances => _performances = null
      case _ =>
        throw new RuntimeException("property with key=" + key + " not (yet) supported by " + this.getClass().getName());
    }

  override def layoutInformation = Song.layoutInformation
}
