#include <finnstr.libgdx.liquidfun.ParticleBodyContact.h>

//@line:10

#include <Box2D/Box2D.h>
	 JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleBodyContact_jniGetIndex(JNIEnv* env, jobject object, jlong addr) {


//@line:33

		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->index;
	

}

JNIEXPORT jlong JNICALL Java_finnstr_libgdx_liquidfun_ParticleBodyContact_jniGetBodyAddr(JNIEnv* env, jobject object, jlong addr) {


//@line:43

		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return (jlong) contact->body;
	

}

JNIEXPORT jlong JNICALL Java_finnstr_libgdx_liquidfun_ParticleBodyContact_jniGetFixtureAddr(JNIEnv* env, jobject object, jlong addr) {


//@line:53

		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return (jlong) contact->fixture;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleBodyContact_jniGetWeight(JNIEnv* env, jobject object, jlong addr) {


//@line:63

		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->weight;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleBodyContact_jniGetNormalX(JNIEnv* env, jobject object, jlong addr) {


//@line:76

		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->normal.x;
	 

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleBodyContact_jniGetNormalY(JNIEnv* env, jobject object, jlong addr) {


//@line:81

		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->normal.y;
	 

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleBodyContact_jniGetMass(JNIEnv* env, jobject object, jlong addr) {


//@line:91

		b2ParticleBodyContact* contact = (b2ParticleBodyContact*)addr;
		return contact->mass;
	

}

