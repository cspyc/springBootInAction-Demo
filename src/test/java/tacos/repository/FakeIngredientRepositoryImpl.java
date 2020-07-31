package tacos.repository;

import tacos.model.Ingredient;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FakeIngredientRepositoryImpl implements IngredientRepository{
    private Collection<Ingredient> ingredients;

    public FakeIngredientRepositoryImpl() {
        initialData();
    }

    private void initialData() {
        ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.CHEESE),
                new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
        );
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return ingredients;
    }

    @Override
    public Ingredient findOne(String id) {
        List<Ingredient> ingredientList =
         ingredients.stream().filter(
                i->id.equals(i.getId())
        ).collect(Collectors.toList());

        return ingredientList.get(0);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        ingredients.add(ingredient);
        return ingredient;
    }
}
