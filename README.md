# Char: The Baker's Friend
## An Android recipe app

A basic recipe app that utilizes Firebase's real-time database in cooperation with Google Android features. This is a course project for the Udacity Android Developer Nanodgree program with ambiguous requirements, so I get to creatively design the app in accordance to the rubric specifications.  

## _Implements_

_Android Technologies:_

- Accessibility
- Spanish, French, & Arabic (RTL) translation
- Espresso UI testing with intent verification & idle resources 
- custom Widget service
- detailFlow activity 
- custom data table implementing Google Material Design principles
- custom vertical stepper with custom animation TransitionManager on two UI scenes
- SVG scalable assets
- Google Material Design icons
- Fragments & manager
- SearchView / SearchManager / Google Voice VUI Search
- SwipeRefreshLayout
- ConstraintLayout cardViews
- tablet + phone UI layouts
- recyclerView with heterogeneous XML layouts
- ShareActionProvider
- ExoPlayer video player with Google Cast
- Facebook Stetho
- Square LeakCanary
- Square Phrase string localization (replaces xliff:g)
- Square Picasso image rendering
- Firebase real-time database
- Parcelable object packaging
- Exoplayer video player with Google Cast
- Butterknife view injection

*** 

# _UI screenshots_

- Phone MainActivity empty list
    - ![](readme/UI_screenshots/empty_main_activity.png)
- Phone MainActivity recipe recyclerView
    - ![]()

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