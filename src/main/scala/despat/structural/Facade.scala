package despat.structural

/**
 * Класс, описывающий 
 * @author  shleger
 */
object Facade extends App {
  val comp = new Computer
  comp.startComputer()
}

class CPU {
  def freeze: Unit = println("freeze")

  def jump(pos: Int): Unit = println("jump: " + pos)

  def execute: Unit = println("execute")
}

class Memory {
  def load(pos: Int, data: Array[Byte]): Unit = println("load:" + pos + " arr: " + data)
}

class HardDrive {
  def read(lba: Long, size: Int): Array[Byte] = {
    println("read");
    return new Array[Byte](10)
  }
}

class Computer {
  val cpu = new CPU
  val memory = new Memory
  val hardDrive = new HardDrive


  def startComputer(): Unit = {
    val BOOT_ADDRESS = 200;
    val BOOT_SECTOR = 10000;
    val SECTOR_SIZE = 10;

    cpu.freeze
    memory.load(BOOT_ADDRESS, hardDrive.read(BOOT_SECTOR, SECTOR_SIZE))
    cpu.jump(BOOT_ADDRESS)
    cpu.execute

  }

}
