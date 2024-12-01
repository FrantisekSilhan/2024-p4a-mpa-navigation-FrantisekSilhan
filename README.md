# Zadání: Rozšíření aplikace o navigaci

V tomto zadání budete rozšiřovat vaši předchozí aplikaci, která zobrazuje data z veřejného API o navigační prvky. Vaším cílem je vytvořit více obrazovek a umožnit uživateli plynulou navigaci mezi nimi. . Důraz je kladen především na správné použití navigace.

## Požadavky implementace
 Použijte knihovnu [**Navigation**](https://developer.android.com/guide/navigation) k implementaci navigace mezi třemi obrazovkami (Domovská obrazovka, Obrazovka kategorií a Detail kategorie). Navigace by měla být intuitivní, včetně funkčních tlačítek pro návrat zpět. Zachovejte strukturu s ViewModelem, kde data získaná z API budou spravována přes `ViewModel`

1.  **Domovská obrazovka**    
	   -   Přidejte jednoduchou domovskou obrazovku s tlačítkem "Vstoupit do aplikace". Po kliknutí na toto tlačítko aplikace přejde na přehledovou obrazovku (např. seznam kategorií jídla).
 2.  **Obrazovka kategorií**    
	   -   Použijte data načtená z API (např. seznam kategorií jídla) a zobrazte je ve formátu mřížky (grid).        
	    -   Po kliknutí na konkrétní kategorii se uživatel přesune na detailní obrazovku této kategorie.        
3.  **Detail kategorie**    
	   -   Vytvořte detailní obrazovku pro kategorii, která zobrazí název kategorie, obrázek a popis.

![Ukázka aplikace](Screen.gif)

## Doplňující informace
**Jetpack Compose Navigation**: https://developer.android.com/develop/ui/compose/navigation


