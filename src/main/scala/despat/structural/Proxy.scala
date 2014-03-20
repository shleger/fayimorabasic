package despat.structural

/**
 * Proxy Pattern
 */
object Proxy extends App {

  val subject: Subject = new ProxySubject
  subject.doWork

}


trait Subject {
  def doWork;
}


class RealSubject extends Subject {
  def doWork: Unit = println("The work is done on a real subject")
}

class ProxySubject extends Subject {

  private lazy val realSubject: RealSubject = new RealSubject;

  def doWork: Unit = {
    println("Do with proxy...")
    realSubject.doWork
  }
}