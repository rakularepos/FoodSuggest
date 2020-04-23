package org.example.beans;

public class Nutrient {

    //Total calories in kcal. Always returned with a precision of 0 decimal places.
    private double calories;

    //Total carbohydrate in grams. Always returned with a precision of 2 decimal places.
    private double carbohydrate;

    //Protein in grams. Always returned with a precision of 2 decimal places.
    private double protein;

    //Total fat in grams. Always returned with a precision of 2 decimal places.
    private double fat;

    //Saturated fat in grams. Always returned with a precision of 3 decimal places.
    private double saturated_fat;

    //Polyunsaturated fat in grams. Always returned with a precision of 3 decimal places.
    private double polyunsaturated_fat;

    //Monounsaturated fat in grams. Always returned with a precision of 3 decimal places.
    private double monounsaturated_fat;

    //Trans fat in grams. Always returned with a precision of 3 decimal places.
    private double trans_fat;

    //Cholesterol in milligrams. Always returned with a precision of 0 decimal places.
    private double cholesterol;

    //Sodium in milligrams. Always returned with a precision of 0 decimal places.
    private double sodium;

    //Potassium in milligrams. Always returned with a precision of 0 decimal places.
    private double potassium;

    //Dietary fiber in grams. Always returned with a precision of 1 decimal place.
    private double fiber;

    //Sugar in grams. Always returned with a precision of 2 decimal places.
    private double sugar;

    //The percentage of daily recommended vitamin A, based on a 2000 calorie diet.
    private double vitamin_a;

    //The percentage of daily recommended vitamin C, based on a 2000 calorie diet.
    private double vitamin_c;

    //The percentage of daily recommended calcium, based on a 2000 calorie diet.
    private double calcium;

    //The percentage of daily recommended iron, based on a 2000 calorie diet.
    private double iron;

    public Nutrient(double calories, double carbohydrate, double protein, double fat, double saturated_fat,
                    double polyunsaturated_fat, double monounsaturated_fat, double trans_fat, double cholesterol,
                    double sodium, double potassium, double fiber, double sugar, double vitamin_a, double vitamin_c,
                    double calcium, double iron) {
        this.calories = calories;
        this.carbohydrate = carbohydrate;
        this.protein = protein;
        this.fat = fat;
        this.saturated_fat = saturated_fat;
        this.polyunsaturated_fat = polyunsaturated_fat;
        this.monounsaturated_fat = monounsaturated_fat;
        this.trans_fat = trans_fat;
        this.cholesterol = cholesterol;
        this.sodium = sodium;
        this.potassium = potassium;
        this.fiber = fiber;
        this.sugar = sugar;
        this.vitamin_a = vitamin_a;
        this.vitamin_c = vitamin_c;
        this.calcium = calcium;
        this.iron = iron;
    }

    public static class NutrientBuilder {
        private double calories;
        private double carbohydrate;
        private double protein;
        private double fat;
        private double saturated_fat;
        private double polyunsaturated_fat;
        private double monounsaturated_fat;
        private double trans_fat;
        private double cholesterol;
        private double sodium;
        private double potassium;
        private double fiber;
        private double sugar;
        private double vitamin_a;
        private double vitamin_c;
        private double calcium;
        private double iron;

        public NutrientBuilder(){}

        public NutrientBuilder setCalories(double calories) {
            this.calories = calories;
            return this;
        }

        public NutrientBuilder setCarbohydrate(double carbohydrate) {
            this.carbohydrate = carbohydrate;
            return this;
        }

        public NutrientBuilder setProtein(double protein) {
            this.protein = protein;
            return this;
        }

        public NutrientBuilder setFat(double fat) {
            this.fat = fat;
            return this;
        }

        public NutrientBuilder setSaturated_fat(double saturated_fat) {
            this.saturated_fat = saturated_fat;
            return this;
        }

        public NutrientBuilder setPolyunsaturated_fat(double polyunsaturated_fat) {
            this.polyunsaturated_fat = polyunsaturated_fat;
            return this;
        }

        public NutrientBuilder setMonounsaturated_fat(double monounsaturated_fat) {
            this.monounsaturated_fat = monounsaturated_fat;
            return this;
        }

        public NutrientBuilder setTrans_fat(double trans_fat) {
            this.trans_fat = trans_fat;
            return this;
        }

        public NutrientBuilder setCholesterol(double cholesterol) {
            this.cholesterol = cholesterol;
            return this;
        }

        public NutrientBuilder setSodium(double sodium) {
            this.sodium = sodium;
            return this;
        }

        public NutrientBuilder setPotassium(double potassium) {
            this.potassium = potassium;
            return this;
        }

        public NutrientBuilder setFiber(double fiber) {
            this.fiber = fiber;
            return this;
        }

        public NutrientBuilder setSugar(double sugar) {
            this.sugar = sugar;
            return this;
        }

        public NutrientBuilder setVitamin_a(double vitamin_a) {
            this.vitamin_a = vitamin_a;
            return this;
        }

        public NutrientBuilder setVitamin_c(double vitamin_c) {
            this.vitamin_c = vitamin_c;
            return this;
        }

        public NutrientBuilder setCalcium(double calcium) {
            this.calcium = calcium;
            return this;
        }

        public NutrientBuilder setIron(double iron) {
            this.iron = iron;
            return this;
        }

        public Nutrient build() {
            return new Nutrient(calories, carbohydrate, protein, fat, saturated_fat,
                    polyunsaturated_fat, monounsaturated_fat, trans_fat, cholesterol,
                    sodium, potassium, fiber, sugar, vitamin_a, vitamin_c,
                    calcium, iron);
        }
    }
}
