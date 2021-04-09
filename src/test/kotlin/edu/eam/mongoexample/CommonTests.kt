import com.fasterxml.jackson.databind.ObjectMapper
import edu.eam.mongoexample.model.Measure
import edu.eam.mongoexample.model.Sensor
import edu.eam.mongoexample.repositories.MeasureRepository
import edu.eam.mongoexample.repositories.SensorRepository
import org.bson.Document
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional
import java.io.File

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
abstract class CommonTests {

    @Autowired
    protected lateinit var objectMapper: ObjectMapper

    @Autowired
    protected lateinit var sensorRepository: SensorRepository

    @Autowired
    protected lateinit var measureRepository: MeasureRepository

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var mongoTemplate: MongoTemplate;

    @BeforeEach
    fun clearDB() {
        mongoTemplate.dropCollection(Measure::class.java)
        mongoTemplate.dropCollection(Sensor::class.java)
    }

    fun fillCollection(collectionName: String, fileName: String) {
        val text = this::class.java.getResource(fileName).readText(Charsets.UTF_8)

        val collection = mongoTemplate.createCollection(collectionName)
        val objects = objectMapper.readTree(text)

        val docs = objects.map {it -> Document.parse(it.toString())}

        collection.insertMany(docs)
    }

}
