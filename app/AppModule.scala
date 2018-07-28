import com.google.inject.AbstractModule
import services.{ MessageService, MessageServiceImpl }

class AppModule extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[MessageService]).to(classOf[MessageServiceImpl])
  }

}
