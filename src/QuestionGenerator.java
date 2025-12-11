/**
 * Generates questions based on Mathematik1 course content
 */
public class QuestionGenerator {
    
    /**
     * Generate comprehensive question bank for Mathematik1
     */
    public static QuestionBank generateMathematik1Questions() {
        QuestionBank bank = new QuestionBank();
        
        // ========== ALGEBRA AND EXPONENTS ==========
        bank.addQuestion(new Question(
            "Algebra - Exponent Rules",
            "Which of the following correctly represents the rule a^(b+c)?",
            new String[]{
                "a^b + a^c",
                "a^b × a^c",
                "(a^b)^c",
                "a^(b×c)"
            },
            1,
            "By definition of exponentiation, a^(b+c) means concatenating the multiplication strings, " +
            "which equals a^b × a^c. For example: 3^(4+2) = 3^6 = (3×3×3×3)×(3×3) = 3^4 × 3^2",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Algebra - Exponent Rules",
            "What is (ab)^c equal to?",
            new String[]{
                "a^c + b^c",
                "a^c × b^c",
                "a^(bc)",
                "(a+b)^c"
            },
            1,
            "(ab)^c = a^c × b^c. Example: (2×3)^4 = (2×3)×(2×3)×(2×3)×(2×3) = " +
            "(2×2×2×2)×(3×3×3×3) = 2^4 × 3^4",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Algebra - Exponent Rules",
            "Is the statement a^b · b^c = (a^b)^c true in general?",
            new String[]{
                "Yes, always true",
                "No, it's false",
                "Only true when a = b",
                "Only true when c = 1"
            },
            1,
            "This is false. Counterexample: Let a=2, b=3, c=4. Left side: 2^3 · 3^4 = 8 · 81 = 648. " +
            "Right side: (2^3)^4 = 2^12 = 4096. Since 648 ≠ 4096, the statement is false.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Algebra - Fractional Exponents",
            "What does a^(-1/n) represent?",
            new String[]{
                "a^n",
                "1/(n-th root of a)",
                "-a^(1/n)",
                "n/a"
            },
            1,
            "a^(-1/n) = 1/a^(1/n) = 1/(n-th root of a). Negative exponent means reciprocal, " +
            "and fractional exponent 1/n means n-th root.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Algebra - Square Root",
            "What is √(x²) equal to?",
            new String[]{
                "x",
                "-x",
                "|x| (absolute value of x)",
                "±x"
            },
            2,
            "√(x²) = |x|. The radical symbol √ represents the principal (non-negative) square root. " +
            "For example: √((-3)²) = √9 = 3 = |-3|, not -3.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Algebra - Binomial Formula",
            "What is (a+b)² equal to?",
            new String[]{
                "a² + b²",
                "a² + 2ab + b²",
                "a² - 2ab + b²",
                "(a+b)(a-b)"
            },
            1,
            "(a+b)² = a² + 2ab + b². This can be proven geometrically by dividing a square with " +
            "side (a+b) into four regions: two squares (a² and b²) and two rectangles (each ab).",
            "easy"
        ));
        
        // ========== PYTHAGOREAN THEOREM ==========
        bank.addQuestion(new Question(
            "Geometry - Pythagorean Theorem",
            "In a right triangle with legs a and b and hypotenuse c, what is the relationship?",
            new String[]{
                "a + b = c",
                "a² + b² = c²",
                "a² - b² = c²",
                "a + b = c²"
            },
            1,
            "The Pythagorean Theorem states a² + b² = c². This can be proven by arranging four " +
            "identical right triangles in two different ways within a square.",
            "easy"
        ));
        
        // ========== TRIGONOMETRY ==========
        bank.addQuestion(new Question(
            "Trigonometry - Unit Circle",
            "On the unit circle, what do the coordinates (cos θ, sin θ) represent?",
            new String[]{
                "The angle θ in degrees",
                "The point on the circle at angle θ from the positive x-axis",
                "The radius of the circle",
                "The tangent of angle θ"
            },
            1,
            "For any angle θ measured counterclockwise from the positive x-axis, the point on the " +
            "unit circle has coordinates (cos θ, sin θ). This is the geometric definition of sine and cosine.",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Trigonometry - Radians",
            "How many radians are in a full circle?",
            new String[]{
                "π",
                "2π",
                "π/2",
                "360"
            },
            1,
            "A full circle is 2π radians (or 360°). In a unit circle, the radian measure equals " +
            "the arc length, so circumference 2πr = 2π(1) = 2π radians.",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Trigonometry - Angles",
            "What is 3π/2 radians in degrees, and where is it on the unit circle?",
            new String[]{
                "90° on positive y-axis",
                "180° on negative x-axis",
                "270° on negative y-axis",
                "360° on positive x-axis"
            },
            2,
            "3π/2 = (3/2) × 180° = 270°. This is three-quarters of a full circle, located on the " +
            "negative y-axis with coordinates (0, -1).",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Trigonometry - Function Properties",
            "What is the period of sin(x) and cos(x)?",
            new String[]{
                "π",
                "2π",
                "π/2",
                "4π"
            },
            1,
            "Both sine and cosine have a period of 2π. This means sin(x + 2π) = sin(x) and " +
            "cos(x + 2π) = cos(x) for all x. The graphs repeat every 2π units.",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Trigonometry - Zeros",
            "Where are the zeros of sin(x)?",
            new String[]{
                "x = nπ (integer multiples of π)",
                "x = π/2 + nπ",
                "x = 2nπ",
                "x = π/4 + nπ"
            },
            0,
            "sin(x) = 0 when x = nπ for any integer n (0, ±π, ±2π, ...). These are points where " +
            "the y-coordinate on the unit circle is zero.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Trigonometry - Tangent",
            "What is the period of tan(x)?",
            new String[]{
                "π/2",
                "π",
                "2π",
                "3π"
            },
            1,
            "tan(x) has period π (not 2π like sine and cosine). This is because tan(x + π) = " +
            "sin(x + π)/cos(x + π) = (-sin x)/(-cos x) = sin x/cos x = tan(x).",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Trigonometry - Tangent Asymptotes",
            "Where does tan(x) have vertical asymptotes?",
            new String[]{
                "x = nπ",
                "x = π/2 + nπ (odd multiples of π/2)",
                "x = 2nπ",
                "Nowhere"
            },
            1,
            "tan(x) = sin(x)/cos(x) is undefined when cos(x) = 0, which occurs at x = π/2 + nπ " +
            "(π/2, 3π/2, 5π/2, ...). These are the vertical asymptotes.",
            "medium"
        ));
        
        // ========== COMPLEX NUMBERS ==========
        bank.addQuestion(new Question(
            "Complex Numbers - Complex Plane",
            "In the complex plane, where are all real numbers located?",
            new String[]{
                "On the imaginary axis",
                "On the real axis (horizontal axis)",
                "In the first quadrant only",
                "At the origin"
            },
            1,
            "Real numbers x are represented as x + 0i, so their imaginary part is zero. This means " +
            "they all lie on the horizontal axis (real axis) of the complex plane.",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Complex Numbers - Arithmetic",
            "How do you divide complex numbers in rectangular form?",
            new String[]{
                "Divide real and imaginary parts separately",
                "Multiply numerator and denominator by the conjugate of the denominator",
                "Convert to polar form first",
                "Use the quadratic formula"
            },
            1,
            "To divide z₁/z₂, multiply both numerator and denominator by the conjugate of z₂. This " +
            "eliminates the imaginary part from the denominator, making calculation easier.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Complex Numbers - Modulus",
            "What does |z| represent for a complex number z = x + yi?",
            new String[]{
                "x + y",
                "√(x² + y²)",
                "x² + y²",
                "x - y"
            },
            1,
            "|z| = √(x² + y²) is the modulus (or absolute value) of z. Geometrically, it represents " +
            "the distance from the origin to the point (x,y) in the complex plane.",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Complex Numbers - Polar Form",
            "In polar form z = r(cos θ + i sin θ), what does r represent?",
            new String[]{
                "The angle (argument)",
                "The modulus (distance from origin)",
                "The real part",
                "The imaginary part"
            },
            1,
            "In polar form, r is the modulus |z| = √(x² + y²), which is the distance from the " +
            "origin. θ is the argument (angle from positive real axis).",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Complex Numbers - De Moivre's Theorem",
            "How do you find the n-th roots of a complex number using De Moivre's Theorem?",
            new String[]{
                "Raise it to the power 1/n",
                "Take n-th root of modulus and divide argument by n, with k = 0,1,...,n-1",
                "Multiply modulus by n and argument by n",
                "Use the quadratic formula"
            },
            1,
            "For z = r(cos θ + i sin θ), the n-th roots are: z_k = ⁿ√r(cos((θ+2πk)/n) + i sin((θ+2πk)/n)) " +
            "for k = 0, 1, ..., n-1. This gives n equally spaced roots on a circle.",
            "hard"
        ));
        
        bank.addQuestion(new Question(
            "Complex Numbers - Conjugate",
            "What is z · z̄ (complex number times its conjugate)?",
            new String[]{
                "0",
                "|z|² (modulus squared)",
                "2z",
                "z²"
            },
            1,
            "For z = x + yi, z̄ = x - yi, so z·z̄ = (x+yi)(x-yi) = x² - (yi)² = x² + y² = |z|². " +
            "This is always a non-negative real number.",
            "medium"
        ));
        
        // ========== VECTORS ==========
        bank.addQuestion(new Question(
            "Vectors - Dot Product",
            "What is the dot product ⃗a · ⃗b for vectors ⃗a = <3,2> and ⃗b = <-4,3>?",
            new String[]{
                "5",
                "-6",
                "-12",
                "18"
            },
            1,
            "⃗a · ⃗b = (3)(-4) + (2)(3) = -12 + 6 = -6. The dot product multiplies corresponding " +
            "components and sums the results.",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Vectors - Magnitude",
            "What is the magnitude of vector ⃗v = <-4, 3>?",
            new String[]{
                "1",
                "5",
                "7",
                "25"
            },
            1,
            "|⃗v| = √((-4)² + 3²) = √(16 + 9) = √25 = 5. The magnitude is calculated using the " +
            "Pythagorean theorem.",
            "easy"
        ));
        
        bank.addQuestion(new Question(
            "Vectors - Orthogonality",
            "When are two non-zero vectors orthogonal (perpendicular)?",
            new String[]{
                "When their sum is zero",
                "When their dot product is zero",
                "When their cross product is zero",
                "When their magnitudes are equal"
            },
            1,
            "Two non-zero vectors are orthogonal if and only if their dot product equals zero: " +
            "⃗a · ⃗b = 0. This is the algebraic condition for perpendicularity.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Vectors - Cross Product",
            "What is a key property of the cross product ⃗a × ⃗b?",
            new String[]{
                "It equals the dot product",
                "It is parallel to both ⃗a and ⃗b",
                "It is perpendicular to both ⃗a and ⃗b",
                "It has magnitude 1"
            },
            2,
            "The cross product ⃗a × ⃗b produces a vector that is perpendicular (orthogonal) to both " +
            "⃗a and ⃗b. This is a defining characteristic of the cross product.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Vectors - Parallel Vectors",
            "How do you find a unit vector in the direction of ⃗v?",
            new String[]{
                "⃗v + 1",
                "⃗v / |⃗v|",
                "⃗v²",
                "|⃗v| · ⃗v"
            },
            1,
            "The unit vector in direction of ⃗v is û = ⃗v/|⃗v|. Dividing by the magnitude scales " +
            "the vector to length 1 while preserving direction.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Vectors - Projection",
            "What is the formula for orthogonal projection of ⃗x onto line spanned by ⃗u?",
            new String[]{
                "⃗x · ⃗u",
                "(⃗u · ⃗x / ⃗u · ⃗u) ⃗u",
                "⃗x × ⃗u",
                "⃗x + ⃗u"
            },
            1,
            "The orthogonal projection is: proj_⃗u(⃗x) = (⃗u·⃗x / ⃗u·⃗u)⃗u. This gives the point " +
            "on the line closest to ⃗x.",
            "hard"
        ));
        
        // ========== ADVANCED TOPICS ==========
        bank.addQuestion(new Question(
            "Mathematical Reasoning - Fallacies",
            "What is wrong with: -1 = √((-1)²) = √1 = 1?",
            new String[]{
                "Nothing, -1 = 1 is correct",
                "√(x²) = |x|, not x, so √((-1)²) = 1, not -1",
                "You can't square negative numbers",
                "The square root of 1 is ±1"
            },
            1,
            "The error is in assuming √((-1)²) = -1. Actually, √(x²) = |x| (absolute value) because " +
            "√ denotes the principal (non-negative) square root. So √((-1)²) = √1 = 1.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Mathematical Reasoning - Division by Zero",
            "Why can't we divide by zero?",
            new String[]{
                "It's just a rule with no reason",
                "Because division by zero is undefined - no unique value satisfies the definition",
                "Because calculators can't do it",
                "We can divide by zero, the result is infinity"
            },
            1,
            "Division by zero is undefined because there's no unique number that satisfies the " +
            "definition. If a/0 = b, then a = b×0 = 0, which only works if a=0, but then any b works!",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Complex Numbers - Roots of Unity",
            "How many 4th roots does any non-zero complex number have?",
            new String[]{
                "1",
                "2",
                "4",
                "Infinite"
            },
            2,
            "Every non-zero complex number has exactly n distinct n-th roots (4 fourth roots, " +
            "3 cube roots, etc.). These are equally spaced around a circle in the complex plane.",
            "medium"
        ));
        
        bank.addQuestion(new Question(
            "Trigonometry - Special Values",
            "What is cos(π)?",
            new String[]{
                "0",
                "1",
                "-1",
                "√2/2"
            },
            2,
            "cos(π) = -1. At angle π (180°), the point on the unit circle is (-1, 0), so the " +
            "x-coordinate (cosine) is -1.",
            "easy"
        ));
        
        return bank;
    }
}
