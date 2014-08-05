package finnstr.libgdx.liquidfun;

public class ParticleSystemDef {

	/** Enable strict Particle/Body contact check.
	* See SetStrictContactCheck for details. */
	public boolean strictContactCheck = false;
	
	/** Set the particle density.
	* See SetDensity for details. */
	public float density = 1.0f;
	
	/** Change the particle gravity scale. Adjusts the effect of the global
	* gravity vector on particles. Default value is 1.0f. */
	public float gravityScale = 1.0f;
	
	/** Set the maximum number of particles.
	* By default, there is no maximum. The particle buffers can continue to
	* grow while b2World's block allocator still has memory.
	* See SetMaxParticleCount for details. */
	public int maxCount = 0;
	
	/** Particles behave as circles with this radius. In Box2D units. */
	public float radius = 1.0f;

	/** Increases pressure in response to compression
	* Smaller values allow more compression */
	public float pressureStrength = 0.05f;

	/** Reduces velocity along the collision normal
	* Smaller value reduces less */
	public float dampingStrength = 1.0f;

	/** Restores shape of elastic particle groups
	* Larger values increase elastic particle velocity */
	public float elasticStrength = 0.25f;

	/** Restores length of spring particle groups
	* Larger values increase sprint particle velocity */
	public float springStrength = 0.25f;

	/** Reduces relative velocity of viscous particles
	* Larger values slow down viscous particles more */
	public float viscousStrength = 0.25f;

	/** Produces pressure on tensile particles
	* 0~0.2. Larger values increase the amount of surface tension. */
	public float surfaceTensionPressureStrength = 0.2f;

	/** Smoothes outline of tensile particles
	* 0~0.2. Larger values result in rounder, smoother, water-drop-like
	* clusters of particles. */
	public float surfaceTensionNormalStrength = 0.2f;

	/** Produces additional pressure on repulsive particles
	* Larger values repulse more
	* Negative values mean attraction. The range where particles behave
	* stably is about -0.2 to 2.0. */
	public float repulsiveStrength = 1.0f;

	/** Produces repulsion between powder particles
	/// Larger values repulse more */
	public float powderStrength = 0.5f;

	/** Pushes particles out of solid particle group
	* Larger values repulse more */
	public float ejectionStrength = 0.5f;

	/** Produces static pressure
	* Larger values increase the pressure on neighboring partilces
	* For a description of static pressure, see
	* http://en.wikipedia.org/wiki/Static_pressure#Static_pressure_in_fluid_dynamics */
	public float staticPressureStrength = 0.2f;

	/** Reduces instability in static pressure calculation
	* Larger values make stabilize static pressure with fewer iterations */
	public float staticPressureRelaxation = 0.2f;

	/** Computes static pressure more precisely
	* See SetStaticPressureIterations for details */
	public int staticPressureIterations = 8;

	/** Determines how fast colors are mixed
	* 1.0f ==> mixed immediately
	* 0.5f ==> mixed half way each simulation step (see b2World::Step()) */
	public float colorMixingStrength = 0.5f;

	/** Whether to destroy particles by age when no more particles can be
	* created.  See #b2ParticleSystem::SetDestructionByAge() for
	* more information. */
	public boolean destroyByAge = true;

	/** Granularity of particle lifetimes in seconds.  By default this is
	* set to (1.0f / 60.0f) seconds.  b2ParticleSystem uses a 32-bit signed
	* value to track particle lifetimes so the maximum lifetime of a
	* particle is (2^32 - 1) / (1.0f / lifetimeGranularity) seconds.
	* With the value set to 1/60 the maximum lifetime or age of a particle is
	* 2.27 years. */
	public float lifetimeGranularity = 1.0f / 60.0f;
	
}
