package guru.springframework.bootstrap;

import guru.springframework.domain.Category;
import guru.springframework.domain.UnitOfMeasure;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootstrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public BootstrapMySQL(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (categoryRepository.count() == 0L) {
            log.debug("Loading Categories");
            loadCategories();
        }

        if (unitOfMeasureRepository.count() == 0L) {
            log.debug("Loading UOMs");
            loadUoms();
        }
    }

    private void loadCategories() {
        createCategory("American");
        createCategory("Mexican");
        createCategory("Fast Food");
        createCategory("Italian");
        createCategory("Chinese");
        createCategory("Indian");
        createCategory("Japanese");
        createCategory("Greek");
        createCategory("Mediterranean");
        createCategory("Thai");
        createCategory("Vietnamese");
    }

    private void loadUoms() {
        createUom("Teaspoon");
        createUom("Tablespoon");
        createUom("Cup");
        createUom("Pinch");
        createUom("Ounce");
        createUom("Dash");
        createUom("Pint");
        createUom("Each");
        createUom("Pound");
        createUom("Unit");
    }

    private void createUom(String uomDescription) {
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setDescription(uomDescription);
        unitOfMeasureRepository.save(uom);
    }

    private void createCategory(String catDescription) {
        Category cat = new Category();
        cat.setDescription(catDescription);
        categoryRepository.save(cat);
    }


}
