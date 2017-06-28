# Char: The Baker's Friend
## An Android recipe app

A basic recipe app that utilizes Firebase's real-time database in cooperation with Google Android features. This is a course project for the Udacity Android Developer Nanodgree program with ambiguous requirements, so I get to creatively design the app in accordance to the rubric specifications.  

## _Implements_

_Android Technologies:_

- Accessibility
- Spanish, French, Arabic (i.e., right to left) translation
- Parcelable object packaging
- Espresso testing
- collection hybrid widget
- Exoplayer video player
- detail flow activity
- SVG assets
- Fragments
- SearchView / SearchManager / Google Voice Search
- SwipeRefreshLayout
- tablet + phone layouts
- PercentRelativeLayouts
- recyclerView with heterogeneous XML layouts
- custom recyclerView adapters
- ShareActionProvider
- Facebook Stetho
- Square LeakCanary
- Square Picasso image rendering
- Firebase real-time database
- Butterknife view injection

***

- Cards for the Launch Activity's recipe list
    - ![](planning/wireframes-GMD/main_activity-recipe_card.png)
    - [source](https://material.io/guidelines/components/cards.html#cards-usage)
    - Actions will include ShareActionProvider, Favorite & an Intent to the onClick's DetailFlow MainActivity
- Data Tables for the DetailFlow's MainActivity ingredients list item
    - ![](planning/wireframes-GMD/data_table_vertical_for_ingredients.png)
    - ![](planning/wireframes-GMD/data_table_header_footer.png)
    - ![](planning/wireframes-GMD/data_table_columns.png)
    - [source](https://material.io/guidelines/components/data-tables.html)
- Vertical Steppers for the DetailFlowActivity's recipe steps & video
    - ![](planning/wireframes-GMD/vertical_stepper_wireframe.png)
    - ![](planning/wireframes-GMD/stepper_usage.png)
    - ![](planning/wireframes-GMD/stepper_UI_redline.png)
    - ![](planning/wireframes-GMD/stepper_redlines.png)
    - [source](https://material.io/guidelines/components/steppers.html#steppers-specs)
- fixed bottom navigation on the phone UI for the DetailFlow's DetailActivity
    - ![](planning/wireframes-GMD/phone_detail_flow_step_instruction_fixed_bottom_navigation.png)
    - ![](planning/wireframes-GMD/phone_detail_flow_steps_elevation.png)
    - ![](planning/wireframes-GMD/phone_detail_flow_steps_redlines.png)
    - [source](https://material.io/guidelines/components/bottom-navigation.html#bottom-navigation-specs)
    - ![](planning/wireframes-GMD/stepper_bottom_navigation_progress.png)
    [source](https://material.io/guidelines/components/steppers.html#steppers-specs)
    - Actions will include 'last' & 'next' with step progress metric
- widget 
    - displays ingredients list for desired recipe (hybrid collections widget)

## _Sources_

- [Udacity Android Developer Nanodegree project rubric](https://review.udacity.com/#!/rubrics/829/view)
- [Google Android Development Guidelines](https://developer.android.com/develop/index.html)
- [Google Material Design Guidelines](https://material.io/guidelines/)
- [Genymotion Android emulator](https://www.genymotion.com/)
- [Creative commons images](https://search.creativecommons.org/)


***

J. Clark (c) June 2017