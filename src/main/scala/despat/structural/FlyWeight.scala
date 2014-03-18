package despat.structural

import scala.collection.mutable.WeakHashMap
import scala.ref.WeakReference
import scala.collection.immutable.HashSet

/**
 * Класс, описывающий 
 * @author  shleger
 */
object FlyWeight extends App {

  val fly1 = FontData.create(1, "font", "color", new HashSet[FontEffect])
  val fly2 = FontData.create(2, "font", "color", new HashSet[FontEffect])
  val fly3 = FontData.create(1, "font", "color", new HashSet[FontEffect])

  println(fly1)
  println(fly2)
  println(fly3)

}


object FontData {
  val flyweightData = new WeakHashMap[FontData, WeakReference[FontData]]

  def create(pointSize: Int, fontFace: String, color: String, effects: Set[FontEffect]): FontData = {
    val it = new FontData(pointSize, fontFace, color, effects)

    if (!flyweightData.contains(it)) {
      flyweightData.put(it, new WeakReference[FontData](it))
    }
    return flyweightData.get(it).get()
  }
}

class FontEffect extends Enumeration {
  val BOLD, ITALIC, SUPERSCRIPT, SUBSCRIPT, STRIKE_THROUGH = Value
}


class FontData private (pointSize: Int, fontFace: String, color: String, effects: Set[FontEffect]) {

  override def hashCode(): Int = (pointSize * 37 + effects.hashCode() * 13) * fontFace.hashCode();

//  override def equals(obj: scala.Any): Boolean = {
//    if (obj.isInstanceOf[FontData]) {
//      if (obj == this) {
//        return true;
//      }
//      val other = obj.asInstanceOf[FontData];
//      return other.pointSize == pointSize && other.fontFace.equals(fontFace) && other.color.equals(color) && other.effects.equals(effects);
//    }
//    return false;
//  }

};
