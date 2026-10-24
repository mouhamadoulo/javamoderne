// =====================================================================
//  RÉVISION : TABLEAUX ET ALGORITHMES DE TRI — Java 25
// ---------------------------------------------------------------------
//  Fichier source "compact" (JEP 512, finalisé en Java 25) :
//    - pas de déclaration de classe, main() d'instance sans String[] args
//    - le module java.base est importé automatiquement (Arrays, List...)
//    - IO.println() remplace System.out.println()
//
//  Lancer :  java TrisTableaux.java
// =====================================================================

// Un record pour décrire chaque algorithme (nom, complexité, stabilité, code)
record Algo(String nom, String complexite, boolean stable, Consumer<int[]> tri) {}

record Etudiant(String nom, double moyenne) {}

final List<Algo> algos = List.of(
        new Algo("Tri à bulles",    "O(n²)       | O(1)",      true,  this::triBulles),
        new Algo("Tri sélection",   "O(n²)       | O(1)",      false, this::triSelection),
        new Algo("Tri insertion",   "O(n²)       | O(1)",      true,  this::triInsertion),
        new Algo("Tri fusion",      "O(n log n)  | O(n)",      true,  this::triFusion),
        new Algo("Tri rapide",      "O(n log n)* | O(log n)",  false, this::triRapide),
        new Algo("Tri par tas",     "O(n log n)  | O(1)",      false, this::triTas),
        new Algo("Tri comptage",    "O(n + k)    | O(k)",      true,  this::triComptage)
);

void main() {
    revisionTableaux();
    traceInsertion();
    demoTris();
    rechercheEtTriObjets();
    benchmark(20_000);
}

// =====================================================================
// 1. LES BASES DES TABLEAUX
// =====================================================================
void revisionTableaux() {
    titre("1. Les tableaux");

    int[] a = new int[5];                 // taille fixe, valeurs par défaut = 0
    int[] b = {5, 3, 8, 1, 9};            // initialisation directe
    var noms = new String[]{"Léa", "Tom"}; // défaut pour les objets = null

    IO.println("a          = " + Arrays.toString(a));
    IO.println("b          = " + Arrays.toString(b));
    IO.println("noms       = " + Arrays.toString(noms));
    IO.println("b.length   = " + b.length + "  (attribut, pas méthode !)");

    // Parcours classique (avec indice) et for-each (lecture seule)
    int somme = 0;
    for (int x : b) somme += x;
    IO.println("somme      = " + somme);
    IO.println("max        = " + Arrays.stream(b).max().orElseThrow());

    // Piège : l'affectation copie la RÉFÉRENCE, pas le contenu
    int[] alias = b;
    int[] copie = b.clone();              // ou Arrays.copyOf(b, b.length)
    alias[0] = 100;
    IO.println("après alias[0]=100 -> b = " + Arrays.toString(b)
            + ", copie = " + Arrays.toString(copie));

    // Comparaison : == compare les références, Arrays.equals le contenu
    int[] c = {100, 3, 8, 1, 9};
    IO.println("b == c ? " + (b == c) + "   Arrays.equals(b, c) ? " + Arrays.equals(b, c));

    // Tableaux 2D (tableaux de tableaux) et tableau irrégulier
    int[][] m = {{1, 2, 3}, {4, 5, 6}};
    IO.println("m          = " + Arrays.deepToString(m) + "  m[1][2] = " + m[1][2]);

    int[][] triangle = new int[4][];
    for (int i = 0; i < triangle.length; i++) {
        triangle[i] = new int[i + 1];
        Arrays.fill(triangle[i], i + 1);
    }
    IO.println("triangle   = " + Arrays.deepToString(triangle));

    // Accès hors limites -> exception
    try {
        IO.println(b[b.length]);
    } catch (ArrayIndexOutOfBoundsException e) {
        IO.println("b[b.length] -> " + e.getMessage());
    }
}

// =====================================================================
// 2. LES ALGORITHMES DE TRI
// =====================================================================

// --- Tri à bulles : on fait "remonter" le plus grand à la fin à chaque passe.
// Optimisation : si aucune permutation pendant une passe, c'est trié.
void triBulles(int[] t) {
    for (int fin = t.length - 1; fin > 0; fin--) {
        boolean echange = false;
        for (int i = 0; i < fin; i++) {
            if (t[i] > t[i + 1]) {
                echanger(t, i, i + 1);
                echange = true;
            }
        }
        if (!echange) return; // meilleur cas O(n) sur un tableau déjà trié
    }
}

// --- Tri par sélection : on cherche le minimum de la partie non triée
// et on le place au début. Toujours O(n²) comparaisons, mais peu d'échanges.
void triSelection(int[] t) {
    for (int i = 0; i < t.length - 1; i++) {
        int iMin = i;
        for (int j = i + 1; j < t.length; j++) {
            if (t[j] < t[iMin]) iMin = j;
        }
        echanger(t, i, iMin);
    }
}

// --- Tri par insertion : comme trier des cartes en main. On insère t[i]
// à sa place dans la partie gauche déjà triée. Très bon sur petits tableaux
// ou tableaux presque triés.
void triInsertion(int[] t) {
    for (int i = 1; i < t.length; i++) {
        int cle = t[i];
        int j = i - 1;
        while (j >= 0 && t[j] > cle) {
            t[j + 1] = t[j]; // décalage vers la droite
            j--;
        }
        t[j + 1] = cle;
    }
}

// --- Tri fusion : "diviser pour régner". On coupe en deux, on trie
// chaque moitié récursivement, puis on fusionne deux listes triées.
void triFusion(int[] t) {
    if (t.length < 2) return;
    fusionRec(t, new int[t.length], 0, t.length - 1);
}

void fusionRec(int[] t, int[] tmp, int g, int d) {
    if (g >= d) return;
    int m = g + (d - g) / 2;          // évite le dépassement de (g + d) / 2
    fusionRec(t, tmp, g, m);
    fusionRec(t, tmp, m + 1, d);

    int i = g, j = m + 1, k = g;
    while (i <= m && j <= d) tmp[k++] = (t[i] <= t[j]) ? t[i++] : t[j++]; // <= : stable
    while (i <= m) tmp[k++] = t[i++];
    while (j <= d) tmp[k++] = t[j++];
    System.arraycopy(tmp, g, t, g, d - g + 1);
}

// --- Tri rapide (quicksort) : on choisit un pivot, on place à gauche
// les plus petits, à droite les plus grands, puis on récurse.
// * Pire cas O(n²) si le pivot est mal choisi (ici : pivot au milieu).
void triRapide(int[] t) {
    rapideRec(t, 0, t.length - 1);
}

void rapideRec(int[] t, int g, int d) {
    if (g >= d) return;
    int p = partition(t, g, d);
    rapideRec(t, g, p - 1);
    rapideRec(t, p + 1, d);
}

int partition(int[] t, int g, int d) {   // schéma de Lomuto
    echanger(t, g + (d - g) / 2, d);      // pivot du milieu placé à la fin
    int pivot = t[d];
    int i = g;
    for (int j = g; j < d; j++) {
        if (t[j] < pivot) echanger(t, i++, j);
    }
    echanger(t, i, d);                    // pivot à sa place définitive
    return i;
}

// --- Tri par tas : on construit un tas-max (le plus grand à la racine),
// puis on échange la racine avec la fin et on "tamise" à nouveau.
void triTas(int[] t) {
    int n = t.length;
    for (int i = n / 2 - 1; i >= 0; i--) tamiser(t, n, i);   // construction
    for (int fin = n - 1; fin > 0; fin--) {
        echanger(t, 0, fin);
        tamiser(t, fin, 0);
    }
}

void tamiser(int[] t, int n, int i) {
    while (true) {
        int max = i, g = 2 * i + 1, d = 2 * i + 2;  // enfants de i
        if (g < n && t[g] > t[max]) max = g;
        if (d < n && t[d] > t[max]) max = d;
        if (max == i) return;
        echanger(t, i, max);
        i = max;
    }
}

// --- Tri par comptage : pas de comparaisons ! On compte les occurrences
// de chaque valeur. Efficace si l'écart k = max - min est petit.
void triComptage(int[] t) {
    if (t.length == 0) return;
    int min = Arrays.stream(t).min().getAsInt();
    int max = Arrays.stream(t).max().getAsInt();
    int[] compte = new int[max - min + 1];
    for (int x : t) compte[x - min]++;
    int k = 0;
    for (int v = 0; v < compte.length; v++) {
        while (compte[v]-- > 0) t[k++] = v + min;
    }
}

void echanger(int[] t, int i, int j) {
    int tmp = t[i];
    t[i] = t[j];
    t[j] = tmp;
}

// Affiche chaque étape du tri par insertion, pour bien visualiser
void traceInsertion() {
    titre("2. Trace du tri par insertion");
    int[] t = {7, 3, 5, 1, 4};
    IO.println("départ : " + Arrays.toString(t));
    for (int i = 1; i < t.length; i++) {
        int cle = t[i], j = i - 1;
        while (j >= 0 && t[j] > cle) { t[j + 1] = t[j]; j--; }
        t[j + 1] = cle;
        IO.println("i=%d (clé %d) : %s".formatted(i, cle, Arrays.toString(t)));
    }
}

void demoTris() {
    titre("3. Tous les tris sur le même tableau");
    int[] original = {29, 10, 14, 37, 13, 5, 88, 1, 42, 10};
    IO.println("original : " + Arrays.toString(original) + "\n");
    IO.println("%-15s %-24s %-7s %s".formatted("Algorithme", "Temps moy. | Mémoire", "Stable", "Résultat"));
    for (Algo a : algos) {
        int[] t = original.clone();   // chaque tri travaille sur sa propre copie
        a.tri().accept(t);
        IO.println("%-15s %-24s %-7s %s".formatted(
                a.nom(), a.complexite(), a.stable() ? "oui" : "non", Arrays.toString(t)));
    }
}

// =====================================================================
// 3. RECHERCHE ET TRI AVEC L'API JAVA
// =====================================================================
void rechercheEtTriObjets() {
    titre("4. Recherche dichotomique et tri d'objets");

    int[] t = {42, 7, 19, 3, 25, 11};
    Arrays.sort(t);  // primitifs : dual-pivot quicksort
    IO.println("trié              : " + Arrays.toString(t));
    IO.println("ma dichotomie(19) : indice " + rechercheDichotomique(t, 19));
    IO.println("binarySearch(19)  : indice " + Arrays.binarySearch(t, 19));
    IO.println("binarySearch(20)  : " + Arrays.binarySearch(t, 20)
            + "  (négatif = absent ; -(point d'insertion) - 1)");

    // Ordre décroissant : impossible directement sur int[], il faut Integer[]
    Integer[] objets = {42, 7, 19, 3};
    Arrays.sort(objets, Comparator.reverseOrder());
    IO.println("décroissant       : " + Arrays.toString(objets));

    // Tri d'objets avec Comparator (tri stable : TimSort)
    Etudiant[] classe = {
            new Etudiant("Zoé", 14.5), new Etudiant("Adam", 12.0),
            new Etudiant("Inès", 14.5), new Etudiant("Hugo", 17.0)
    };
    Arrays.sort(classe, Comparator.comparingDouble(Etudiant::moyenne).reversed()
            .thenComparing(Etudiant::nom));
    IO.println("classement :");
    for (int i = 0; i < classe.length; i++) {
        var e = classe[i];
        String mention = switch ((int) e.moyenne()) {
            case 16, 17, 18, 19, 20 -> "Très bien";
            case 14, 15 -> "Bien";
            case 12, 13 -> "Assez bien";
            default -> "-";
        };
        IO.println("  %d. %-5s %5.1f  %s".formatted(i + 1, e.nom(), e.moyenne(), mention));
    }
}

// Le tableau DOIT être trié. Complexité O(log n).
int rechercheDichotomique(int[] t, int cible) {
    int g = 0, d = t.length - 1;
    while (g <= d) {
        int m = g + (d - g) / 2;
        if (t[m] == cible) return m;
        if (t[m] < cible) g = m + 1;
        else d = m - 1;
    }
    return -1;
}

// =====================================================================
// 4. MINI-BENCHMARK + VÉRIFICATION
// =====================================================================
void benchmark(int n) {
    titre("5. Benchmark sur " + n + " entiers aléatoires");
    int[] donnees = new Random(42).ints(n, 0, 10_000).toArray();
    int[] attendu = donnees.clone();
    Arrays.sort(attendu);

    for (Algo a : algos) {
        int[] t = donnees.clone();
        long debut = System.nanoTime();
        a.tri().accept(t);
        double ms = (System.nanoTime() - debut) / 1_000_000.0;
        String ok = Arrays.equals(t, attendu) ? "OK" : "ERREUR";
        IO.println("%-15s %9.2f ms   %s".formatted(a.nom(), ms, ok));
    }
}

void titre(String s) {
    IO.println("\n=== " + s + " " + "=".repeat(Math.max(0, 55 - s.length())));
}